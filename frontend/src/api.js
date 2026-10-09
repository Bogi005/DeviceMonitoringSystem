import axios from "axios";

const API_BASE_URL = 'http://localhost:8080/api';
const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    }
});

// DEVICES
export const getDevicesPage = (params = {} ) =>
    api.get('/devices', { params });
export const getDeviceById = (id) =>
    api.get(`/devices/${id}`);
export const getAllDevices = () =>
    api.get('/devices/all');
export const updateDevice = (id, data) =>
    api.put(`/devices/${id}`, data);
export const createDevice = (data) =>
    api.post('/devices', data);
export const deleteDevice = (id) =>
    api.delete(`/devices/${id}`);

// READINGS
export const getReadingsPage = (deviceId, params = {} ) =>
    api.get(`/devices/${deviceId}/readings`, { params });
export const getAllReadings = (deviceId) =>
    api.get(`/devices/${deviceId}/readings/all`);
export const addReading = (deviceId, data) =>
    api.post(`/devices/${deviceId}/readings`, data);

// ALARMS
export const getAllAlarmsPage = ( params = {} ) =>
    api.get(`/alarms`, { params });
export const getDeviceAlarmsPage = ( deviceId, params = {} ) =>
    api.get(`/alarms/devices/${deviceId}`, { params });
export const resolveAlarm = (id) =>
    api.patch(`/alarms/devices/${id}`);

// STATS
export const getSystemStats = () =>
    api.get(`/stats`);
export const getDeviceStats = ( deviceId ) =>
    api.get(`/stats/${deviceId}`);

export default api;