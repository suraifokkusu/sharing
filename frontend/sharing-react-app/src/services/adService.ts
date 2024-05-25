import axios from 'axios';
import config from '../config';
import { AdvertisementDto } from '../dto/AdvertisementDto';

// Создаем экземпляр axios с базовым URL из конфигурации
const api = axios.create({
  baseURL: config.apiUrl,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'ngrok-skip-browser-warning': "69420"
  }
});

// Функция для получения всех объявлений
export const getAllAdvertisements = async (): Promise<AdvertisementDto[]> => {
  try {
    // Отправляем GET запрос на endpoint /api/advertisements
    const response = await api.get<AdvertisementDto[]>('/api/advertisements');
    console.log('API response:', response.data);
    return response.data; // Возвращаем данные ответа
  } catch (error) {
    // Обрабатываем ошибки
    console.error("Fetch ads error:", error);
    throw error;
  }
};

// Функция для получения объявления по ID
export const getAdById = async (id: number): Promise<AdvertisementDto> => {
  try {
    // Отправляем GET запрос на endpoint /api/advertisements/{id}
    const response = await api.get<AdvertisementDto>(`/api/advertisements/${id}`);
    return response.data; // Возвращаем данные ответа
  } catch (error) {
    // Обрабатываем ошибки
    console.error("Fetch ad error:", error);
    throw error;
  }
};
