function InputField( {name, placeholderText, value, onChange} ) {
    return (
        <input
            name={name}
            type="text"
            placeholder={placeholderText}
            value={value}
            onChange={onChange}
            required
            style={{ padding: '8px', borderRadius: '4px', border: '1px solid #ccc' }}
        />
    )
}
export default InputField;