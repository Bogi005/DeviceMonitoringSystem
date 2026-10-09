import {useState, useEffect} from "react";
import {getAllReadings, getDeviceAlarmsPage, getReadingsPage, resolveAlarm} from "../api.js";
import {CartesianGrid, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis} from "recharts";
import ReadingForm from "./ReadingForm.jsx";
import PageControls from "./PageControls.jsx";

function DeviceDetails({device, onError}) {
    const [readings, setReadings] = useState([]);
    const [chartReadings, setChartReadings] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [refreshTrigger, setRefreshTrigger] = useState(0);
    const pageSize = 10;
    const [alarms, setAlarms] = useState([]);
    const [alarmPage, setAlarmPage] = useState(0);
    const [alarmTotalPage, setAlarmTotalPage] = useState(0);

    const fetchReadings = async () => {
        try {
            const response = await getReadingsPage(
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
        getDeviceAlarmsPage(device.id, {
            page: alarmPage,
            size: 5
        })
            .then(data => {
                setAlarms(data.data.content);
                setAlarmTotalPage(data.data.totalPages);
            })
            .catch(error => {
                onError(error.response?.data?.message || 'Error occurred while fetching device alarms.');
            });
    }, [device, alarmPage, refreshTrigger]);

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
    }, [device, refreshTrigger]);

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

    const formatTimestamp = (timestamp) => {
        if (!timestamp) return '-';

        const date = new Date(timestamp);

        return new Intl.DateTimeFormat('sr-RS', { // or 'en-US' for English format
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
            hour12: false, // Use 24-hour clock
        }).format(date);
    };

    const resolve = async (id) => {
        await resolveAlarm(id);
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

            <PageControls
                page={page}
                totalPages={totalPages}
                setPage={setPage}
            />

            <h3>Alarms</h3>
            <table border="1" cellPadding="8" style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                    <tr>
                        <th>Type</th>
                        <th>Message</th>
                        <th>Time</th>
                        <th>Resolved</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    { alarms.map((alarm) => (
                        <tr key={alarm.id}>
                            <td>{alarm.alarmType}</td>
                            <td>{alarm.message}</td>
                            <td>{formatTimestamp(alarm.alarmTime)}</td>
                            <td>{alarm.resolved ? "Resolved" : ""}</td>
                            <td>
                                <button onClick={() => {
                                    resolve(alarm.id)
                                        .then(() => {})
                                }}>
                                    resolve
                                </button>
                            </td>
                        </tr>
                    )) }
                </tbody>
            </table>

            <PageControls
                page={alarmPage}
                totalPages={alarmTotalPage}
                setPage={setAlarmPage}
            />
        </div>
    )
}
export default DeviceDetails;