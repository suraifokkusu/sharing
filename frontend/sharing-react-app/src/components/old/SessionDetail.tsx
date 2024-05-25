// src/components/SessionDetail.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { SessionDto } from '../dto/SessionDto'; // Импортируем SessionDto

const SessionDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>(); // Получаем параметр id из URL
  const navigate = useNavigate();
  const [session, setSession] = useState<SessionDto | null>(null); // Состояние для хранения данных сессии
  const [error, setError] = useState<string | null>(null); // Состояние для хранения ошибок

  useEffect(() => {
    const fetchSession = async () => {
      try {
        const response = await axios.get<SessionDto>(`http://localhost:8080/api/sessions/${id}`); // Запрос для получения данных сессии
        const sessionData = {
          ...response.data,
          sessionDate: new Date(response.data.sessionDate).toISOString().slice(0, -1) // Преобразуем дату в нужный формат для input
        };
        setSession(sessionData); // Сохраняем данные сессии в состояние
      } catch (error) {
        setError('Error fetching session'); // Устанавливаем ошибку, если запрос не удался
        console.error('Error fetching session:', error);
      }
    };
    fetchSession();
  }, [id]); // Запускается при изменении id

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/sessions/${id}`); // Отправляем запрос на удаление сессии
      navigate('/sessions'); // Перенаправляем на страницу со списком сессий после успешного удаления
    } catch (error) {
      setError('Error deleting session'); // Устанавливаем ошибку, если запрос не удался
      console.error('Error deleting session:', error);
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setSession({ ...session, [name]: value } as SessionDto);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (session) {
      try {
        const sessionData = {
          ...session,
          sessionDate: new Date(session.sessionDate).toISOString() // Преобразуем дату в формат ISO
        };
        const response = await axios.put('http://localhost:8080/api/sessions', sessionData);
        console.log('Session updated:', response.data);
      } catch (error) {
        setError('Error updating session');
        console.error('Error updating session:', error);
      }
    }
  };

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Session Detail</h2>
      {session ? (
        <form onSubmit={handleSubmit}>
          <div>
            <label>Platform: {session.platform}</label>
            <input type="text" name="platform" value={session.platform} onChange={handleChange} />
          </div>
          <div>
            <label>Session Date:</label>
            <input type="datetime-local" name="sessionDate" value={session.sessionDate} onChange={handleChange} />
          </div>
          <button type="submit">Update Session</button>
          <button type="button" onClick={handleDelete}>Delete Session</button>
        </form>
      ) : (
        <div>Loading...</div> // Сообщение о загрузке данных
      )}
    </div>
  );
};

export default SessionDetail;
