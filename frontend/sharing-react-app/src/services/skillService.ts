import axios from 'axios';
import config from '../config';
import { SkillDto } from '../dto/SkillDto';

const api = axios.create({
  baseURL: config.apiUrl,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'ngrok-skip-browser-warning': '69420'
  }
});

// Функция для получения всех навыков
export const getAllSkills = async (): Promise<SkillDto[]> => {
  try {
    const response = await api.get<SkillDto[]>('/api/skills');
    return response.data;
  } catch (error) {
    console.error('Fetch skills error:', error);
    throw error;
  }
};

// Функция для получения навыка по ID
export const getSkillById = async (id: number): Promise<SkillDto> => {
  try {
    const response = await api.get<SkillDto>(`/api/skills/${id}`);
    return response.data;
  } catch (error) {
    console.error('Fetch skill error:', error);
    throw error;
  }
};

// Функция для создания нового навыка
export const createSkill = async (skill: SkillDto): Promise<SkillDto> => {
  try {
    const response = await api.post<SkillDto>('/api/skills', skill);
    return response.data;
  } catch (error) {
    console.error('Create skill error:', error);
    throw error;
  }
};

// Функция для обновления навыка по ID
export const updateSkill = async (skill: SkillDto): Promise<SkillDto> => {
  try {
    const response = await api.put<SkillDto>('/api/skills', skill);
    return response.data;
  } catch (error) {
    console.error('Update skill error:', error);
    throw error;
  }
};

// Функция для удаления навыка по ID
export const deleteSkill = async (id: number): Promise<void> => {
  try {
    await api.delete(`/api/skills/${id}`);
  } catch (error) {
    console.error('Delete skill error:', error);
    throw error;
  }
};
