// src/components/SkillDetail.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { SkillDto } from '../dto/SkillDto.ts'; // Импортируем SkillDto

const SkillDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>(); // Получаем параметр id из URL
  const navigate = useNavigate();
  const [skill, setSkill] = useState<SkillDto | null>(null); // Состояние для хранения данных навыка
  const [error, setError] = useState<string | null>(null); // Состояние для хранения ошибок

  useEffect(() => {
    const fetchSkill = async () => {
      try {
        const response = await axios.get<SkillDto>(`http://localhost:8080/api/skills/${id}`); // Запрос для получения данных навыка
        setSkill(response.data); // Сохраняем данные навыка в состояние
      } catch (error) {
        setError('Error fetching skill'); // Устанавливаем ошибку, если запрос не удался
        console.error('Error fetching skill:', error);
      }
    };
    fetchSkill();
  }, [id]); // Запускается при изменении id

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/skills/${id}`); // Отправляем запрос на удаление навыка
      navigate('/skills'); // Перенаправляем на страницу со списком навыков после успешного удаления
    } catch (error) {
      setError('Error deleting skill'); // Устанавливаем ошибку, если запрос не удался
      console.error('Error deleting skill:', error);
    }
  };

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Skill Detail</h2>
      {skill ? (
        <div>
          <p>Skill Name: {skill.skillName}</p>
          <button onClick={handleDelete}>Delete Skill</button> {/* Кнопка для удаления навыка */}
        </div>
      ) : (
        <div>Loading...</div> // Сообщение о загрузке данных
      )}
    </div>
  );
};

export default SkillDetail;
