// device = { id, serialNumber, name, location}
// isSelected = boolean
// onSelect = function called on selection
// onDelete = function called on deletion
function DeviceRow( {device, isSelected, onSelect, onDelete } ) {
    return (
        <tr style={{ backgroundColor: isSelected ? '#e6f7ff' : 'transparent' }}>
            <td>{device.serialNumber}</td>
            <td>{device.name}</td>
            <td>{device.location}</td>
            <td>
                <button onClick={() => onSelect(device)} style={{ marginRight: '8px' }}>
                    { isSelected ? 'Hide readings' : 'Show readings'}
                </button>
            </td>
            <td>
                <button onClick={() => onDelete(device.id)} style={{ color: 'red' }}>
                    Delete
                </button>
            </td>
        </tr>
    )
}

export default DeviceRow;