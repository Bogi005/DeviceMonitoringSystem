import { useState, useEffect } from "react";
import {deleteDevice, getDevicesPage} from "../api.js";
import InputField from "./InputField.jsx";
import DeviceRow from "./DeviceRow.jsx";
import PageControls from "./PageControls.jsx";

// devices = list of devices
function DeviceList({ refresh, selectedDevice, onSelectDevice, onError }) {
    const [devices, setDevices] = useState([]);
    const [serialNumber, setSerialNumber] = useState('');
    const [location, setLocation] = useState('');

    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const pageSize = 5;

    const fetchDevices = async () => {
        try {
            const response = await getDevicesPage({
                serialNumber: serialNumber,
                location: location,
                page: page,
                size: pageSize,
                sort: 'id,asc'
            });
            setDevices(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            onError(error.response?.data?.message || 'Error fetching devices.');
        }
    }

    useEffect(() => {
        fetchDevices();
    }, [page, serialNumber, location, refresh]);

    const handleDeleteDevice = async (id) => {
            try {
                await deleteDevice(id);
                if (selectedDevice?.id === id) {
                    onSelectDevice(null);
                }
                await fetchDevices();
            }
            catch (error) {
                onError(error.response?.data?.message || 'Error deleting the device.');
            }
        }

    return (
        <div style={{ padding: '20px' }}>
            <h2>Devices</h2>

            <div style={{ display: 'flex', gap: '10px', marginBottom: '20px'}}>
                <h3>Search devices:</h3>
                <div style={{ display: 'flex', flex: 1, gap: '10px', paddingLeft: '50px', justifyContent: 'left' }}>
                    <InputField
                        name="SerialNumberFilter"
                        value={serialNumber}
                        placeholderText="Search by serial number"
                        onChange={(e) => {setSerialNumber(e.target.value); setPage(0);}}
                    />
                    <InputField
                        name="LocationFilter"
                        value={location}
                        placeholderText="Search by location"
                        onChange={(e) => {setLocation(e.target.value); setPage(0);}}
                    />
                </div>
            </div>

            <table border="1" cellPadding="8" style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                    <tr>
                        <th>Serial Number</th>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {devices.map((device) => (
                        <DeviceRow
                            key={device.id}
                            device={device}
                            isSelected={selectedDevice?.id === device.id}
                            onSelect={onSelectDevice}
                            onDelete={handleDeleteDevice}
                        />
                    ))}
                </tbody>
            </table>

            <PageControls
                page={page}
                totalPages={totalPages}
                setPage={setPage}
            />
        </div>
    );
}

export default DeviceList;