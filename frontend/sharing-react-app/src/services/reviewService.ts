import axios from 'axios';
import config from '../config';
import { ReviewDto } from '../dto/ReviewDto';

const api = axios.create({
  baseURL: config.apiUrl,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'ngrok-skip-browser-warning': '69420'
  }
});

// Функция для получения всех отзывов
export const getAllReviews = async (): Promise<ReviewDto[]> => {
  try {
    const response = await api.get<ReviewDto[]>('/api/reviews');
    return response.data;
  } catch (error) {
    console.error('Fetch reviews error:', error);
    throw error;
  }
};

// Функция для получения отзыва по ID
export const getReviewById = async (id: number): Promise<ReviewDto> => {
  try {
    const response = await api.get<ReviewDto>(`/api/reviews/${id}`);
    return response.data;
  } catch (error) {
    console.error('Fetch review error:', error);
    throw error;
  }
};

// Функция для создания нового отзыва
export const createReview = async (review: ReviewDto): Promise<ReviewDto> => {
  try {
    const response = await api.post<ReviewDto>('/api/reviews', review);
    return response.data;
  } catch (error) {
    console.error('Create review error:', error);
    throw error;
  }
};

// Функция для обновления отзыва по ID
export const updateReview = async (review: ReviewDto): Promise<ReviewDto> => {
  try {
    const response = await api.put<ReviewDto>('/api/reviews', review);
    return response.data;
  } catch (error) {
    console.error('Update review error:', error);
    throw error;
  }
};

// Функция для удаления отзыва по ID
export const deleteReview = async (id: number): Promise<void> => {
  try {
    await api.delete(`/api/reviews/${id}`);
  } catch (error) {
    console.error('Delete review error:', error);
    throw error;
  }
};
