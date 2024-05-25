import React, { useState, useEffect } from 'react';
import './Sessions.css'; // Ссылка на файл стилей

function Sessions() {
  const [sessions, setSessions] = useState([]);

  useEffect(() => {
    // Здесь должен быть вызов API для загрузки данных о сессиях
    setSessions([{ id: 1, name: "Session 1" }, { id: 2, name: "Session 2" }]);
  }, []);

  return (
    <div className="sessions">
      <h1>Active Sessions</h1>
      <ul>
        {sessions.map(session => (
          <li key={session.id}>{session.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default Sessions;
