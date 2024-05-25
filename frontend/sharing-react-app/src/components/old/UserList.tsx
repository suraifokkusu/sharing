// src/components/UserList.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { UserDto } from '../dto/UserDto'; // Импортируем UserDto

const UserList: React.FC = () => {
  const [users, setUsers] = useState<UserDto[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get<UserDto[]>('http://localhost:8080/api/users');
        setUsers(response.data);
      } catch (error) {
        setError('Error fetching users');
        console.error('Error fetching users:', error);
      }
    };
    fetchUsers();
  }, []);

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>User List</h2>
      <ul>
        {users.map(user => (
          <li key={user.userId}>{user.name} - {user.email}</li>
        ))}
      </ul>
    </div>
  );
};

export default UserList;
