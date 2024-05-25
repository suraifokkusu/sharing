import React, { useEffect, useState } from 'react';
import { fetchSessions } from '../api/sessionApi';

function Sessions() {
  const [sessions, setSessions] = useState([]);

  useEffect(() => {
    fetchSessions().then(setSessions);
  }, []);

  return (
    <div>
      <h1>Sessions</h1>
      <ul>
        {sessions.map(session => (
          <li key={session.id}>{session.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default Sessions;
