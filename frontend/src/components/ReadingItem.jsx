function ReadingItem({ reading }){
    const formattedDate = new Date(reading.timestamp).toLocaleString();
    return (
        <li style={{ padding: '6px 0', borderBottom: '1px solid #eee' }}>
            <strong>
                Value: {reading.value}
            </strong>
            <small style={{ color: '#666', marginLeft: '10px' }}>
                {formattedDate}
            </small>
        </li>
    )
}

export default ReadingItem;