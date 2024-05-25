import React from 'react';
import { AuthProvider } from './context/AuthContext';
import AppRouter from './AppRouter';

const AppRoutes: React.FC = () => {
  return (
    <AuthProvider>
      <AppRouter />
    </AuthProvider>
  );
};

export default AppRoutes;
