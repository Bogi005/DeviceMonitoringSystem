import ReadingForm from "./ReadingForm.jsx";
import ReadingItem from "./ReadingItem.jsx";

function ReadingSection({ selectedDevice, readings, isLoading, onAddReading, onError }) {
    return (
        <section style={{ border: '1px solid #007bff', padding: '15px', borderRadius: '5px', marginTop: '20px' }}>
            <h2>
                Readings for: {selectedDevice.name} ({selectedDevice.serialNumber})
            </h2>
            <ReadingForm onAddReading={onAddReading} onError={onError} />
            <h3>
                Reading history
            </h3>
            {isLoading ? (
                <p style={{ color: '#007bff', fontStyle: 'italic' }}>
                    Loading readings...
                </p>
            ) : readings.length === 0 ? (
                <p>No readings found.</p>
            ) : (
                <ul style={{ listStyle: 'none', paddingLeft: 0 }}>
                    {readings.map(reading => (
                        <ReadingItem key={reading.id} reading={reading} />
                    ))}
                </ul>
            )}
        </section>
    )
}

export default ReadingSection;