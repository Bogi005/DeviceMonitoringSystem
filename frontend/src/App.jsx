import { useState } from "react";
import DeviceForm from "./components/DeviceForm.jsx";
import DeviceList from "./components/DeviceList.jsx";
// import ReadingSection from "./components/ReadingSection.jsx";

function App() {
    const [error, setError] = useState('');
    const [selectedDevice, setSelectedDevice] = useState(null);
    const [showAddForm, setShowAddForm] = useState(false);

    // // GET /api/devices
    // const fetchDevices = async () => {
    //     try {
    //         const response = await getDevices();
    //         setDevices(response.data);
    //     }
    //         // eslint-disable-next-line no-unused-vars
    //     catch (error) {
    //         setError("Error fetching devices.");
    //     }
    // };
    //
    // // load devices upon loading the page
    // useEffect(() => {
    //     //fetchDevices();
    //     getDevices().then(
    //         response => {setDevices(response.data);}
    //     ).catch(() => {setError("Error fetching devices.");});
    // }, []);
    //
    // // PUT /api/devices
    // const handleCreateDevice = async (deviceData) => {
    //     await createDevice(deviceData);
    //     setError('')
    //     await fetchDevices();
    // };
    //
    // // DELETE /api/devices/{id}
    // const handleDeleteDevice = async (id) => {
    //     try {
    //         await deleteDevice(id);
    //         if (selectedDevice?.id === id) {
    //             setSelectedDevice(null);
    //         }
    //         await fetchDevices();
    //     }
    //         // eslint-disable-next-line no-unused-vars
    //     catch (error) {
    //         setError("Error deleting the device.");
    //     }
    // }
    //
    // // GET /api/devices/{id}/readings
    // const handleSelectDevice = async (device) => {
    //     selectedDevice?.id === device.id ? setSelectedDevice(null) : setSelectedDevice(device);
    //     setReadings([]);
    //     setLoading(true);
    //     try {
    //         const response = await getReadings(device.id);
    //         setReadings(response.data);
    //         setError('')
    //     }
    //         // eslint-disable-next-line no-unused-vars
    //     catch (error) {
    //         setError("Error fetching readings.");
    //     }
    //     setLoading(false);
    // }
    //
    // // PUT /api/devices/{id}/readings
    // const handleAddReading = async (value) => {
    //     if (!selectedDevice) return;
    //     await addReading(selectedDevice.id, {value});
    //     setError('')
    //     const response = await getReadings(selectedDevice.id);
    //     setReadings(response.data);
    // }

    return (
        <div style={{ fontFamily: 'Arial, sans-serif', backgroundColor: '#f4f6f8', minHeight: '100vh', margin: 0 }}>
            <header style={{
                backgroundColor: '#1e293b',
                color: 'white',
                padding: '15px 30px',
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
                boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
            }}>
                <h1 style={{color: 'white'}}>Device Monitoring System</h1>

                {error && <div style={{ color: 'red', marginBottom: '15px' }}>{error}</div>}

                {!selectedDevice && (
                    <button
                        onClick={() => setShowAddForm(!showAddForm)}
                        style={{
                            backgroundColor: showAddForm ? '#64748b' : '#2563eb',
                            color: 'white',
                            border: 'none',
                            padding: '8px 16px',
                            borderRadius: '6px',
                            cursor: 'pointer',
                            fontWeight: 'bold'
                        }}
                    >
                        {showAddForm ? 'Close form' : 'Add device'}
                    </button>
                )}
            </header>
            <main style={{ maxWidth: '1200px', margin: '20px auto', padding: '0 20px' }}>
                {
                    selectedDevice ? (
                        <p>k</p>
                    )
                    : (
                        <div>
                            {
                                showAddForm && <DeviceForm
                                    onError={setError}
                                />
                            }
                            <DeviceList
                                selectedDevice={selectedDevice}
                                onSelectDevice={(device) => setSelectedDevice(device)}
                                onError={setError}
                            />
                        </div>
                    )
                }




                {/*{*/}
                {/*    selectedDevice && (*/}
                {/*        <ReadingSection*/}
                {/*            selectedDevice={selectedDevice}*/}
                {/*            readings={readings}*/}
                {/*            isLoading={loading}*/}
                {/*            onAddReading={handleAddReading}*/}
                {/*            onError={setError}*/}
                {/*        />*/}
                {/*    )*/}
                {/*}*/}
            </main>
        </div>
    );
}

export default App;