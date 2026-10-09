import { useEffect, useState } from 'react';
import {getSystemStats} from "../api.js";

export default function StatisticsPage() {
    const [stats, setStats] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        getSystemStats().then((data) => {
            setStats(data.data);
            console.log(data);
        })
            .then(() => setLoading(false))
            .catch((err) => setError(err?.response?.data?.message || "Error fetching stats"));
    }, []);

    if (loading) return <div style={{ padding: '20px' }}>Loading Stats...</div>;
    if (error) return <div style={{ padding: '20px', color: 'red' }}>Error: {error}</div>;

    return (
        <div style={{ padding: '24px', fontFamily: 'Arial, sans-serif' }}>
            <h1>System Statistics</h1>

            <div style={{ display: 'flex', gap: '16px', marginBottom: '24px' }}>
                <div style={cardStyle}>
                    <h3>Total Devices</h3>
                    <p style={cardValStyle}>{stats.totalDeviceCount}</p>
                </div>
                <div style={cardStyle}>
                    <h3>Total Readings</h3>
                    <p style={cardValStyle}>{stats.totalReadingCount}</p>
                </div>
                <div style={{ ...cardStyle, borderColor: stats.totalUnresolvedAlarmCount > 0 ? '#e53e3e' : '#cbd5e0' }}>
                    <h3>Active Alarms</h3>
                    <p style={{ ...cardValStyle, color: stats.totalUnresolvedAlarmCount > 0 ? '#e53e3e' : '#2b6cb0' }}>
                        {stats.totalUnresolvedAlarmCount}
                    </p>
                </div>
            </div>

            {stats.devicesWithoutReadings.length > 0 && (
                <div style={{ padding: '12px 16px', backgroundColor: '#fffaf0', borderLeft: '4px solid #dd6b20', marginBottom: '24px' }}>
                    <strong>Devices without readings:</strong> {stats.devicesWithoutReadings.join(', ')}
                </div>
            )}

            <h2>Device Statistics</h2>
            <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '12px' }}>
                <thead>
                <tr style={{ backgroundColor: '#edf2f7', textAlign: 'left' }}>
                    <th style={thTdStyle}>Device Name</th>
                    <th style={thTdStyle}>Serial Number</th>
                    <th style={thTdStyle}>Broj Čitanja</th>
                    <th style={thTdStyle}>Prosječna Vrijednost</th>
                    <th style={thTdStyle}>Maksimalna Vrijednost</th>
                    <th style={thTdStyle}>Aktivni Alarmi</th>
                </tr>
                </thead>
                <tbody>
                {stats.deviceStatistics.map((device) => (
                    <tr key={device.deviceId} style={{ borderBottom: '1px solid #e2e8f0' }}>
                        <td style={thTdStyle}><strong>{device.deviceName}</strong></td>
                        <td style={thTdStyle}>{device.serialNumber}</td>
                        <td style={thTdStyle}>{device.readingCount}</td>
                        <td style={thTdStyle}>{device.avgValue}</td>
                        <td style={thTdStyle}>{device.maxValue}</td>
                        <td style={thTdStyle}>
                <span style={{ color: device.unresolvedAlarmCount > 0 ? '#e53e3e' : '#38a169', fontWeight: 'bold' }}>
                  {device.unresolvedAlarmCount}
                </span>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

const cardStyle = {
    flex: 1,
    padding: '16px',
    borderRadius: '8px',
    border: '1px solid #cbd5e0',
    backgroundColor: '#f7fafc',
    boxShadow: '0 2px 4px rgba(0,0,0,0.05)'
};

const cardValStyle = {
    fontSize: '28px',
    fontWeight: 'bold',
    marginTop: '8px',
    marginBottom: '0',
    color: '#2b6cb0'
};

const thTdStyle = {
    padding: '12px',
    borderBottom: '1px solid #e2e8f0'
};