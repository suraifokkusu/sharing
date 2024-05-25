import axios from 'axios';
import config from '../config';
import {User} from '../types/userTypes'
// Функция для входа пользователя
export const loginUser = async (username: string, password: string) => {
  try {
    const response = await axios.post(`${config.apiUrl}/auth/login`, { username, password });
    return response.data;
  } catch (error) {
    console.error("Login error:", error);
    throw error;
  }
};

// Функция для регистрации пользователя
export const registerUser = async (username: string, password: string) => {
  try {
    const response = await axios.post(`${config.apiUrl}/auth/register`, { username, password });
    return response.data;
  } catch (error) {
    console.error("Registration error:", error);
    throw error;
  }
};

// Функция для получения профиля пользователя
export const getUserProfile = async () => {
  try {
    const response = await axios.get(`${config.apiUrl}/user/profile`);
    return response.data;
  } catch (error) {
    console.error("Profile fetch error:", error);
    throw error;
  }
};

// Функция для получения всех пользователей
export const getAllUsers = async () => {
  const response = await axios.get(`${config.apiUrl}/users`);
  return response.data;
};

// Функция для получения пользователя по ID
export const getUserById = async (id: number) => {
  const response = await axios.get(`${config.apiUrl}/users/${id}`);
  return response.data;
};

// Функция для создания нового пользователя
export const createUser = async (user: any) => {
  const response = await axios.post(`${config.apiUrl}/users`, user);
  return response.data;
};

// Функция для обновления пользователя по ID
export const updateUser = async (user: any) => {
  const response = await axios.put(`${config.apiUrl}/users`, user);
  return response.data;
};

// Функция для удаления пользователя по ID
export const deleteUser = async (id: number) => {
  await axios.delete(`${config.apiUrl}/users/${id}`);
};

export const updateUserProfile = async (userData: { name: string; email: string }): Promise<User> => {
  try {
    const response = await axios.put(`${config.apiUrl}/api/users/profile`, userData, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    });
    return response.data;
  } catch (error) {
    throw new Error('Failed to update user profile');
  }
};