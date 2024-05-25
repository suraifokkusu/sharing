// src/App.tsx
import React from 'react';
// import AppRouter from './AppRouter';
// import { AuthProvider } from './context/AuthContext';
import './styles/global.css'; // Импортируем глобальные стили
import Navbar from './components/Navbar/Navbar';

const App: React.FC = () => {
  return (

    <Navbar />
    // <AuthProvider>
    //   <AppRouter />
    // </AuthProvider>
  );
};

export default App; // Экспорт по умолчанию
