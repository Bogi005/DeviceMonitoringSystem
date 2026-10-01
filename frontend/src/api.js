import axios from "axios";

const API_BASE_URL = 'http://localhost:8080/api';
const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    }
});

export const createDevice = (data) => api.post('/devices', data);
export const getDevices = () => api.get('/devices');
export const getDeviceById = (id) => api.get(`/devices/${id}`);
export const deleteDevice = (id) => api.delete(`/devices/${id}`);

export const addReading = (deviceId, data) => api.post(`/devices/${deviceId}/readings`, data);
export const getReadings = (deviceId) => api.get(`/devices/${deviceId}/readings`)

export default api;