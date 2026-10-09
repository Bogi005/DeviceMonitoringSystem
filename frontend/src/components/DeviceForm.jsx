import { useState } from "react";
import InputField from "./InputField.jsx";
import SubmitButton from "./SubmitButton.jsx";
import {createDevice} from "../api.js";

function DeviceForm( { onAdd, onError} ) {
    const [data, setData] = useState({
        serialNumber: "",
        name: "",
        location: ""
    });

    // event = submit
    // event.preventDefault() = don't refresh the page
    // await = wait for response
    // onDeviceCreated(data) = sends data to App for further processing
    // error.response?.data?.message = if there is an error message, use it, if not, use the default one
    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await createDevice(data);
            setData({
                serialNumber: "",
                name: "",
                location: ""
            });
            onAdd();
        }
        catch (error) {
            onError(error.response?.data?.message || 'Error occurred while submitting device form');
        }
    }

    // ...data, serialNumber: event.target.value = copy data object and change the serialNumber value
    return (
        <section style={{ border: '1px solid #ccc', padding: '15px', borderRadius: '5px', marginBottom: '20px' }}>
            <h2>
                New Device
            </h2>
            <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '10px', justifyContent: 'center' }}>
                <InputField
                    name="SerialNumber"
                    placeholderText="Serial Number"
                    value={data.serialNumber}
                    onChange={
                        (event) => setData(
                            {
                                ...data,
                                serialNumber: event.target.value
                            }
                        )
                    }
                />
                <InputField
                    name="DeviceName"
                    placeholderText="Device Name"
                    value={data.name}
                    onChange={
                        (event) => setData(
                            {
                                ...data,
                                name: event.target.value
                            }
                        )
                    }
                />
                <InputField
                    name="Location"
                    placeholderText="Location"
                    value={data.location}
                    onChange={
                        (event) => setData(
                            {
                                ...data,
                                location: event.target.value
                            }
                        )
                    }
                />
                <SubmitButton
                    label="Add Device"
                />
            </form>
        </section>
    )
}

export default DeviceForm;