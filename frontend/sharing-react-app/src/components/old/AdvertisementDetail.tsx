// src/components/AdvertisementDetail.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { AdvertisementDto } from '../dto/AdvertisementDto'; // Импортируем AdvertisementDto

const AdvertisementDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>(); // Получаем параметр id из URL
  const navigate = useNavigate();
  const [ad, setAd] = useState<AdvertisementDto | null>(null); // Состояние для хранения данных объявления
  const [error, setError] = useState<string | null>(null); // Состояние для хранения ошибок

  useEffect(() => {
    const fetchAd = async () => {
      try {
        const response = await axios.get<AdvertisementDto>(`http://localhost:8080/api/advertisements/${id}`); // Запрос для получения данных объявления
        setAd(response.data); // Сохраняем данные объявления в состояние
      } catch (error) {
        setError('Error fetching advertisement'); // Устанавливаем ошибку, если запрос не удался
        console.error('Error fetching advertisement:', error);
      }
    };
    fetchAd();
  }, [id]); // Запускается при изменении id

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/advertisements/${id}`); // Отправляем запрос на удаление объявления
      navigate('/ads'); // Перенаправляем на страницу со списком объявлений после успешного удаления
    } catch (error) {
      setError('Error deleting advertisement'); // Устанавливаем ошибку, если запрос не удался
      console.error('Error deleting advertisement:', error);
    }
  };

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Advertisement Detail</h2>
      {ad ? (
        <div>
          <p>Category: {ad.category}</p>
          <p>Description: {ad.description}</p>
          <p>Type: {ad.adType}</p>
          <p>Status: {ad.status}</p>
          <button onClick={handleDelete}>Delete Advertisement</button> {/* Кнопка для удаления объявления */}
        </div>
      ) : (
        <div>Loading...</div> // Сообщение о загрузке данных
      )}
    </div>
  );
};

export default AdvertisementDetail;
