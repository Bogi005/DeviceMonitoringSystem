import { useState } from "react";
import DeviceForm from "./components/DeviceForm.jsx";
import DeviceList from "./components/DeviceList.jsx";
import DeviceDetails from "./components/DeviceDetails.jsx";

function App() {
    const [error, setError] = useState('');
    const [selectedDevice, setSelectedDevice] = useState(null);
    const [showAddForm, setShowAddForm] = useState(false);
    const [refreshTrigger, setRefreshTrigger] = useState(0);

    const handleRefresh = () => {
        setRefreshTrigger(value => value + 1);
    }

    return (
        <div style={{ fontFamily: 'Arial, sans-serif', backgroundColor: '#f4f6f8', minHeight: '80vh', margin: 0}}>
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
                {selectedDevice && (
                    <button
                        onClick={() => setSelectedDevice(null)}
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
                        Go back
                    </button>
                )}
            </header>
            <main style={{ maxWidth: '1200px', margin: '20px auto', padding: '0 20px' }}>
                {
                    selectedDevice ? (
                        <DeviceDetails
                            device={selectedDevice}
                            onError={setError}
                        />
                    )
                    : (
                        <div>
                            {
                                showAddForm && <DeviceForm
                                    onAdd={handleRefresh}
                                    onError={setError}
                                />
                            }
                            <DeviceList
                                refresh={refreshTrigger}
                                selectedDevice={selectedDevice}
                                onSelectDevice={(device) => setSelectedDevice(device)}
                                onError={setError}
                            />
                        </div>
                    )
                }
            </main>
        </div>
    );
}

export default App;