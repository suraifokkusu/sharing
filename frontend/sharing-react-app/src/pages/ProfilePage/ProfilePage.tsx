// ProfilePage.tsx
import React from 'react';

interface User {
  userId: string;
  name: string;
  email: string;
}

interface ProfilePageProps {
  user: User | null;
}

const ProfilePage: React.FC<ProfilePageProps> = ({ user }) => {
  if (!user) {
    return <div>Please log in to view your profile.</div>;
  }

  return (
    <div>
      <h2>{user.name}'s Profile</h2>
      <p>Email: {user.email}</p>
      <p>User ID: {user.userId}</p>
    </div>
  );
};

export default ProfilePage;
