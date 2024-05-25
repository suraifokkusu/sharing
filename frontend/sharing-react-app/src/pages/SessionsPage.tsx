import React, { useEffect, useState } from 'react';
import { getAllSessions, createSession, updateSession, deleteSession } from '../services/sessionService';
import { SessionDto } from '../dto/SessionDto';

const SessionsPage: React.FC = () => {
  const [sessions, setSessions] = useState<SessionDto[]>([]);
  const [newSession, setNewSession] = useState<Partial<SessionDto>>({
    adId: 0,
    sessionDate: '',
    platform: ''
  });
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchSessions = async () => {
      try {
        const data = await getAllSessions();
        setSessions(data);
      } catch (err) {
        setError('Failed to fetch sessions.');
      }
    };

    fetchSessions();
  }, []);

  const handleCreate = async () => {
    try {
      const createdSession = await createSession(newSession as SessionDto);
      setSessions([...sessions, createdSession]);
      setNewSession({ adId: 0, sessionDate: '', platform: '' });
    } catch (err) {
      setError('Failed to create session.');
    }
  };

  const handleUpdate = async (session: SessionDto) => {
    try {
      const updatedSession = await updateSession(session);
      setSessions(sessions.map(s => (s.sessionId === updatedSession.sessionId ? updatedSession : s)));
    } catch (err) {
      setError('Failed to update session.');
    }
  };

  const handleDelete = async (id: number | null) => {
    if (id === null) return;
    try {
      await deleteSession(id);
      setSessions(sessions.filter(session => session.sessionId !== id));
    } catch (err) {
      setError('Failed to delete session.');
    }
  };

  return (
    <div>
      <h2>Sessions Page</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <div>
        <h3>Create New Session</h3>
        <input
          type="number"
          placeholder="Ad ID"
          value={newSession.adId}
          onChange={(e) => setNewSession({ ...newSession, adId: Number(e.target.value) })}
        />
        <input
          type="datetime-local"
          placeholder="Session Date"
          value={newSession.sessionDate}
          onChange={(e) => setNewSession({ ...newSession, sessionDate: e.target.value })}
        />
        <input
          type="text"
          placeholder="Platform"
          value={newSession.platform}
          onChange={(e) => setNewSession({ ...newSession, platform: e.target.value })}
        />
        <button onClick={handleCreate}>Create</button>
      </div>
      <ul>
        {sessions.map(session => (
          <li key={session.sessionId}>
            <p>Date: {session.sessionDate}</p>
            <p>Platform: {session.platform}</p>
            <button onClick={() => handleDelete(session.sessionId)}>Delete</button>
            <button onClick={() => handleUpdate({ ...session, platform: 'Updated Platform' })}>Update</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SessionsPage;
