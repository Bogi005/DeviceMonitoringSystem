function SubmitButton( {label} ) {
    return (
        <button
            type="submit"
            style={{ padding: '8px 16px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
        >
            {label}
        </button>
    )
}
export default SubmitButton;