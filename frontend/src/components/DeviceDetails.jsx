import {useState, useEffect} from "react";
import {getAllReadings, getReadings} from "../api.js";
import {CartesianGrid, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis} from "recharts";
import ReadingForm from "./ReadingForm.jsx";

function DeviceDetails({device, onError}) {
    const [readings, setReadings] = useState([]);
    const [chartReadings, setChartReadings] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [refreshTrigger, setRefreshTrigger] = useState(0);
    const pageSize = 10;

    const fetchReadings = async () => {
        try {
            const response = await getReadings(
                device.id,
                {
                    page: page,
                    size: pageSize,
                    sort: 'timestamp,desc'
                }
            );
            setReadings(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            onError(error.response?.data?.message || 'Error occurred while fetching device readings.');
        }
    }

    useEffect(() => {
        // eslint-disable-next-line react-hooks/set-state-in-effect
        if(device) fetchReadings().then(() => {});
    }, [device, page, refreshTrigger]);

    useEffect(() => {
        const fetchAllReadings = async () => {
            try {
                const response = await getAllReadings(device.id);
                setChartReadings(response.data);
                onError('');
            }
            catch(error) {
                onError(error.response?.data?.message || 'Error occurred while fetching device readings.');
            }
        }
        
        fetchAllReadings().then(() => {});
    }, [device]);

    const chartData = [...chartReadings].reverse().map(
        r => ({
            time: new Date(r.timestamp).toLocaleTimeString(
                [], {hour: '2-digit', minute: '2-digit', second: '2-digit'}
            ),
            value: r.value
        })
    );

    const handleAddReading = () => {
        setRefreshTrigger(refreshTrigger => refreshTrigger + 1);
    }

    return (
        <div style={{ padding: '20px' }}>
            <h2>Device: {device.name} ({device.serialNumber})</h2>
            <p><strong>Location: </strong>{device.location}</p>

            <ReadingForm
                device={device}
                onError={onError}
                onAdd={handleAddReading}
            />

            <h3>Readings Chart</h3>
            <div style={{ width: '100%', height: 300, marginBottom: '30px' }}>
                <ResponsiveContainer width="100%" height="100%">
                    <LineChart data={chartData}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="time" />
                        <YAxis />
                        <Tooltip />
                        <Line type="monotone" dataKey="value" stroke="#8884d8" strokeWidth={2} />
                    </LineChart>
                </ResponsiveContainer>
            </div>

            <h3>Readings History</h3>
            <table border="1" cellPadding="8" style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                    <tr>
                        <th>Date and time</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    {readings.map((r) => (
                        <tr key={r.id}>
                            <td>{new Date(r.timestamp).toLocaleString()}</td>
                            <td>{r.value}</td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div style={{ marginTop: '15px', display: 'flex', gap: '10px', alignItems: 'center' }}>
                <button disabled={page === 0} onClick={() => setPage(p => p - 1)}>
                    Previous
                </button>
                <span>Page {page + 1} out of {totalPages || 1}</span>
                <button disabled={page >= totalPages - 1} onClick={() => setPage(p => p + 1)}>
                    Next
                </button>
            </div>
        </div>
    )
}
export default DeviceDetails;