// src/components/UserDetail.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { UserDto } from '../dto/UserDto';

const UserDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>(); // Получаем параметр id из URL
  const navigate = useNavigate();
  const [user, setUser] = useState<UserDto | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await axios.get<UserDto>(`http://localhost:8080/api/users/${id}`);
        setUser(response.data);
      } catch (error) {
        setError('Error fetching user');
        console.error('Error fetching user:', error);
      }
    };
    fetchUser();
  }, [id]);

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/users/${id}`);
      navigate('/users'); // Перенаправление на список пользователей после успешного удаления
    } catch (error) {
      setError('Error deleting user');
      console.error('Error deleting user:', error);
    }
  };

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>User Detail</h2>
      {user ? (
        <div>
          <p>Name: {user.name}</p>
          <p>Email: {user.email}</p>
          <p>Telegram ID: {user.telegramId}</p>
          <p>Profile Text: {user.profileText}</p>
          <p>Rating: {user.rating}</p>
          <p>Currency Balance: {user.currencyBalance}</p>
          <button onClick={handleDelete}>Delete User</button>
        </div>
      ) : (
        <div>Loading...</div>
      )}
    </div>
  );
};

export default UserDetail;
