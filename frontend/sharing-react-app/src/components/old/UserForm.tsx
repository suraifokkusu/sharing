// src/components/UserForm.tsx

import React, { useState } from 'react';
import axios from 'axios';
import { UserDto } from '../dto/UserDto'; // Импортируем UserDto

const UserForm: React.FC = () => {
  const [user, setUser] = useState<UserDto>({
    userId: null,
    name: '',
    email: '',
    telegramId: '',
    profileText: '',
    rating: 0,
    currencyBalance: 0
  });
  const [error, setError] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/users', user);
      console.log('User created:', response.data);
    } catch (error) {
      setError('Error creating user');
      console.error('Error creating user:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name:</label>
        <input type="text" name="name" value={user.name} onChange={handleChange} />
      </div>
      <div>
        <label>Email:</label>
        <input type="text" name="email" value={user.email} onChange={handleChange} />
      </div>
      <div>
        <label>Telegram ID:</label>
        <input type="text" name="telegramId" value={user.telegramId} onChange={handleChange} />
      </div>
      <div>
        <label>Profile Text:</label>
        <input type="text" name="profileText" value={user.profileText} onChange={handleChange} />
      </div>
      <div>
        <label>Rating:</label>
        <input type="number" name="rating" value={user.rating} onChange={handleChange} />
      </div>
      <div>
        <label>Currency Balance:</label>
        <input type="number" name="currencyBalance" value={user.currencyBalance} onChange={handleChange} />
      </div>
      {error && <div>{error}</div>}
      <button type="submit">Submit</button>
    </form>
  );
};

export default UserForm;
    