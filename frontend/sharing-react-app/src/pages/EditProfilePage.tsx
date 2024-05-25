// src/pages/EditProfilePage.tsx
import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';

const EditProfilePage: React.FC = () => {
  const { user, login } = useAuth();
  const [name, setName] = useState(user?.name || '');
  const [email, setEmail] = useState(user?.email || '');

  const handleSave = () => {
    if (user) {
      login({
        ...user,
        name,
        email
      });
    }
  };

  return (
    <div className="container">
      <h2>Edit Profile</h2>
      <div>
        <label>
          Name:
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </label>
      </div>
      <div>
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </label>
      </div>
      <button onClick={handleSave}>Save</button>
    </div>
  );
};

export default EditProfilePage;
