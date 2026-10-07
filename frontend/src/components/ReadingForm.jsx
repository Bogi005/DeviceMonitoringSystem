import { useState } from "react";
import SubmitButton from "./SubmitButton.jsx";
import {addReading} from "../api.js";

function ReadingForm({ device, onAdd, onError }) {
    const [value, setValue] = useState("");

    const handleSubmit = async (event) => {
        if (!device) return;
        event.preventDefault();
        try {
            const data = {
                value: parseFloat(value)
            };
            await addReading(device.id, data);
            setValue("");
            onAdd();
            onError('');
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
                step="0.01"
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