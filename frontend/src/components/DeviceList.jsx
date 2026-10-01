import DeviceRow from "./DeviceRow.jsx";

// devices = list of devices
function DeviceList({ devices, selectedDevice, onSelectDevice, onDeleteDevice }) {
    if (devices.length === 0) {
        return (
            <p>
                No devices found.
            </p>
        )
    }
    return (
        <section style={{ marginBottom: '20px' }}>
            <h2>
                All Devices
            </h2>
            <table border="1" cellPadding="8" style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                    <tr style={{ backgroundColor: '#f2f2f2' }}>
                        <th>
                            Device ID
                        </th>
                        <th>
                            Serial number
                        </th>
                        <th>
                            Name
                        </th>
                        <th>
                            Location
                        </th>
                        <th colSpan={2}>
                            Actions
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {devices.map(device => (
                        <DeviceRow
                            key={device.id}
                            device={device}
                            isSelected={selectedDevice?.id === device.id}
                            onSelect={onSelectDevice}
                            onDelete={onDeleteDevice}
                        />
                    ))}
                </tbody>
            </table>
        </section>
    )
}

export default DeviceList;