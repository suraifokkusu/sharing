// src/components/SessionForm.tsx

import React, { useState } from 'react';
import axios from 'axios';
import { SessionDto } from '../dto/SessionDto'; // Импортируем SessionDto

const SessionForm: React.FC = () => {
  const [session, setSession] = useState<SessionDto>({
    sessionId: null,
    adId: 1, // Замените на реальный идентификатор объявления
    sessionDate: '',
    platform: ''
  });
  const [error, setError] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setSession({ ...session, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const sessionData = {
        ...session,
        sessionDate: new Date(session.sessionDate).toISOString() // Преобразуем дату в формат ISO
      };
      const response = await axios.post('http://localhost:8080/api/sessions', sessionData);
      console.log('Session created:', response.data);
    } catch (error) {
      setError('Error creating session');
      console.error('Error creating session:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Platform:</label>
        <input type="text" name="platform" value={session.platform} onChange={handleChange} />
      </div>
      <div>
        <label>Session Date:</label>
        <input type="datetime-local" name="sessionDate" value={session.sessionDate} onChange={handleChange} />
      </div>
      {error && <div>{error}</div>}
      <button type="submit">Submit</button>
    </form>
  );
};

export default SessionForm;
