import { useState } from "react";
import App from "../App.jsx";
import StatisticsPage from "./StatisticsPage.jsx";

function PageController() {
    const [page, setPage] = useState("home");

    return (
        <div style={styles.pageWrapper}>
            <header style={styles.header}>
                <div style={styles.navContainer}>
                    <div style={styles.brand}>
                        <span style={styles.brandIcon}>📊</span>
                        <span style={styles.brandTitle}>Device Monitoring</span>
                    </div>

                    <nav style={styles.nav}>
                        <button
                            onClick={() => setPage("home")}
                            style={{
                                ...styles.navButton,
                                ...(page === "home" ? styles.activeNavButton : {}),
                            }}
                        >
                            Devices
                        </button>
                        <button
                            onClick={() => setPage("stats")}
                            style={{
                                ...styles.navButton,
                                ...(page === "stats" ? styles.activeNavButton : {}),
                            }}
                        >
                            Statistics
                        </button>
                    </nav>
                </div>
            </header>

            <main style={styles.mainContent}>
                {page === "home" && <App />}
                {page === "stats" && <StatisticsPage />}
            </main>
        </div>
    );
}

const styles = {
    pageWrapper: {
        fontFamily: "'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif",
        backgroundColor: "#f8fafc",
        minHeight: "100vh",
        color: "#1e293b",
    },
    header: {
        backgroundColor: "#ffffff",
        borderBottom: "1px solid #e2e8f0",
        boxShadow: "0 1px 3px 0 rgba(0, 0, 0, 0.05)",
        position: "sticky",
        top: 0,
        zIndex: 10,
    },
    navContainer: {
        maxWidth: "1200px",
        margin: "0 auto",
        padding: "0 24px",
        height: "64px",
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
    },
    brand: {
        display: "flex",
        alignItems: "center",
        gap: "10px",
    },
    brandIcon: {
        fontSize: "20px",
    },
    brandTitle: {
        fontWeight: "700",
        fontSize: "18px",
        color: "#0f172a",
        letterSpacing: "-0.02em",
    },
    nav: {
        display: "flex",
        gap: "8px",
        backgroundColor: "#f1f5f9",
        padding: "4px",
        borderRadius: "8px",
    },
    navButton: {
        border: "none",
        background: "transparent",
        padding: "8px 16px",
        fontSize: "14px",
        fontWeight: "500",
        color: "#64748b",
        borderRadius: "6px",
        cursor: "pointer",
        transition: "all 0.2s ease",
    },
    activeNavButton: {
        backgroundColor: "#ffffff",
        color: "#2563eb",
        fontWeight: "600",
        boxShadow: "0 1px 2px 0 rgba(0, 0, 0, 0.05)",
    },
    mainContent: {
        maxWidth: "1200px",
        margin: "0 auto",
        padding: "24px",
    },
};

export default PageController;