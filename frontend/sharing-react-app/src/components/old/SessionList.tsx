// src/components/SessionList.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { SessionDto } from '../dto/SessionDto'; // Импортируем SessionDto
import { Link } from 'react-router-dom';

const SessionList: React.FC = () => {
  const [sessions, setSessions] = useState<SessionDto[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchSessions = async () => {
      try {
        const response = await axios.get<SessionDto[]>('http://localhost:8080/api/sessions');
        setSessions(response.data);
      } catch (error) {
        setError('Error fetching sessions');
        console.error('Error fetching sessions:', error);
      }
    };
    fetchSessions();
  }, []);

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Session List</h2>
      <ul>
        {sessions.map(session => (
          <li key={session.sessionId}>
            {session.platform} - {session.sessionDate}
            <Link to={`/sessions/${session.sessionId}`}>Details</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SessionList;
