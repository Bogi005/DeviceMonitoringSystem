import { useState, useEffect } from "react";
import {createDevice, getDevices, deleteDevice, getReadings, addReading} from "./api.js";
import DeviceForm from "./components/DeviceForm.jsx";
import DeviceList from "./components/DeviceList.jsx";
import ReadingSection from "./components/ReadingSection.jsx";

function App() {
    const [error, setError] = useState('');
    const [devices, setDevices] = useState([]);
    const [readings, setReadings] = useState([]);
    const [selectedDevice, setSelectedDevice] = useState(null);
    const [loading, setLoading] = useState(false);

    // GET /api/devices
    const fetchDevices = async () => {
        try {
            const response = await getDevices();
            setDevices(response.data);
        }
            // eslint-disable-next-line no-unused-vars
        catch (error) {
            setError("Error fetching devices.");
        }
    };

    // load devices upon loading the page
    useEffect(() => {
        //fetchDevices();
        getDevices().then(
            response => {setDevices(response.data);}
        ).catch(() => {setError("Error fetching devices.");});
    }, []);

    // PUT /api/devices
    const handleCreateDevice = async (deviceData) => {
        await createDevice(deviceData);
        setError('')
        await fetchDevices();
    };

    // DELETE /api/devices/{id}
    const handleDeleteDevice = async (id) => {
        try {
            await deleteDevice(id);
            if (selectedDevice?.id === id) {
                setSelectedDevice(null);
            }
            await fetchDevices();
        }
            // eslint-disable-next-line no-unused-vars
        catch (error) {
            setError("Error deleting the device.");
        }
    }

    // GET /api/devices/{id}/readings
    const handleSelectDevice = async (device) => {
        selectedDevice?.id === device.id ? setSelectedDevice(null) : setSelectedDevice(device);
        setReadings([]);
        setLoading(true);
        try {
            const response = await getReadings(device.id);
            setReadings(response.data);
            setError('')
        }
            // eslint-disable-next-line no-unused-vars
        catch (error) {
            setError("Error fetching readings.");
        }
        setLoading(false);
    }

    // PUT /api/devices/{id}/readings
    const handleAddReading = async (value) => {
        if (!selectedDevice) return;
        await addReading(selectedDevice.id, {value});
        setError('')
        const response = await getReadings(selectedDevice.id);
        setReadings(response.data);
    }

    return (
        <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif', maxWidth: '800px', margin: '0 auto' }}>
            <h1>Device Monitoring System</h1>

            {error && <div style={{ color: 'red', marginBottom: '15px' }}>{error}</div>}

            <DeviceForm
                onDeviceCreated={handleCreateDevice}
                onError={setError}
            />
            <DeviceList
                devices={devices}
                selectedDevice={selectedDevice}
                onSelectDevice={handleSelectDevice}
                onDeleteDevice={handleDeleteDevice}
            />
            {
                selectedDevice && (
                    <ReadingSection
                        selectedDevice={selectedDevice}
                        readings={readings}
                        isLoading={loading}
                        onAddReading={handleAddReading}
                        onError={setError}
                    />
                )
            }
        </div>
    );
}

export default App;