// src/context/AuthContext.tsx
import React, { createContext, useContext, useState, ReactNode } from 'react';

// Определяем интерфейс для пользователя
interface User {
  userId: string;
  name: string;
  email: string;
}

// Определяем интерфейс для контекста аутентификации
interface AuthContextType {
  user: User | null;
  login: (user: User) => void;
  logout: () => void;
}

// Создаем контекст
const AuthContext = createContext<AuthContextType | undefined>(undefined);

// Создаем провайдер для контекста
export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);

  const login = (user: User) => {
    setUser(user);
  };

  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// Хук для использования контекста аутентификации
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
