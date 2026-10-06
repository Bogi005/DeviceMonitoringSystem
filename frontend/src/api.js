import axios from "axios";

const API_BASE_URL = 'http://localhost:8080/api';
const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    }
});

export const getDevices = ( params = {} ) => {
    return api.get('/devices', { params });
}
export const getDeviceById = (id) => api.get(`/devices/${id}`);
export const getAllDevices = () => api.get('/devices/all');
export const updateDevice = (id, data) => api.put(`/devices/${id}`, data);
export const createDevice = (data) => api.post('/devices', data);
export const deleteDevice = (id) => api.delete(`/devices/${id}`);

export const getReadings = ( deviceId, params = {} ) => {
    return api.get(`/devices/${deviceId}/readings`, { params });
}
export const getAllReadings = (deviceId) => api.get(`/devices/${deviceId}/readings`)
export const addReading = (deviceId, data) => api.post(`/devices/${deviceId}/readings`, data);

export default api;