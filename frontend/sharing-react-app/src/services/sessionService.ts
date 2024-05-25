import axios from 'axios';
import config from '../config';
import { SessionDto } from '../dto/SessionDto';

const api = axios.create({
  baseURL: config.apiUrl,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'ngrok-skip-browser-warning': "69420"
  }
});

export const getAllSessions = async (): Promise<SessionDto[]> => {
  try {
    const response = await api.get<SessionDto[]>('/api/sessions');
    return response.data;
  } catch (error) {
    console.error('Fetch sessions error:', error);
    throw error;
  }
};

export const getSessionById = async (id: number): Promise<SessionDto> => {
  try {
    const response = await api.get<SessionDto>(`/api/sessions/${id}`);
    return response.data;
  } catch (error) {
    console.error('Fetch session error:', error);
    throw error;
  }
};

export const createSession = async (session: SessionDto): Promise<SessionDto> => {
  try {
    const response = await api.post<SessionDto>('/api/sessions', session);
    return response.data;
  } catch (error) {
    console.error('Create session error:', error);
    throw error;
  }
};

export const updateSession = async (session: SessionDto): Promise<SessionDto> => {
  try {
    const response = await api.put<SessionDto>('/api/sessions', session);
    return response.data;
  } catch (error) {
    console.error('Update session error:', error);
    throw error;
  }
};

export const deleteSession = async (id: number): Promise<void> => {
  try {
    await api.delete(`/api/sessions/${id}`);
  } catch (error) {
    console.error('Delete session error:', error);
    throw error;
  }
};
