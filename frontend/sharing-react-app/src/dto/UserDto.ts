// src/dto/UserDto.ts

// Описание интерфейса для DTO пользователя
export interface UserDto {
    userId: number | null; // Идентификатор пользователя, может быть null для новых пользователей
    name: string; // Имя пользователя
    email: string; // Email пользователя
    telegramId: string; // Telegram ID пользователя
    profileText: string; // Текст профиля пользователя
    rating: number; // Рейтинг пользователя
    currencyBalance: number; // Баланс внутренней валюты пользователя
  }
  