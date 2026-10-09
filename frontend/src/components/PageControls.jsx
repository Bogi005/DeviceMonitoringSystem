function PageControls({page, setPage, totalPages}) {
    return (
        <div style={{ marginTop: '15px', display: 'flex', gap: '10px', alignItems: 'center', justifyContent: 'space-between' }}>
            <button disabled={page === 0} onClick={() => setPage(p => p - 1)}>
                Previous
            </button>
            <span>Page {page + 1} out of {totalPages || 1}</span>
            <button disabled={page >= totalPages - 1} onClick={() => setPage(p => p + 1)}>
                Next
            </button>
        </div>
    )
}
export default PageControls;