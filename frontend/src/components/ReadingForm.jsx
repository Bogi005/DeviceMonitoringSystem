import { useState } from "react";
import SubmitButton from "./SubmitButton.jsx";

function ReadingForm({ onAddReading, onError }) {
    const [value, setValue] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await onAddReading(parseFloat(value));
            setValue("");
        }
        catch (error) {
            onError(error.response?.data?.message || 'Error occurred while submitting device form');
        }
    }

    return (
        <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '10px', marginBottom: '15px', justifyContent: 'center', alignItems: 'center' }}>
            <input
                name="Reading value"
                type="number"
                step="any"
                placeholder="Enter reading value"
                value={value}
                onChange={(event) => setValue(event.target.value)}
                required
                style={{ padding: '8px', borderRadius: '4px', border: '1px solid #ccc' }}
            />
            <SubmitButton label="Add reading"/>
        </form>
    )
}

export default ReadingForm;