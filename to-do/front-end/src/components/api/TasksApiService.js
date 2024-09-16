import { apiClient } from "./ApiClient";




export const retrieveTaskById = (username, taskId) => apiClient.get(`/users/${username.username}/todos/${taskId}`)

export const retrieveTasks = (username) => apiClient.get(`/users/${username.username}/todos`)

export const deleteTaskApi = (username, taskId) => apiClient.delete(`/users/${username}/todos/${taskId}`)

export const updateTaskApi = (data) => apiClient.put(`users/${data.username.username}/todos/${data.id}`, data)

export const postTaskApi = (data, token) => apiClient.post(`/users/${data.username.username}/todos`, data)