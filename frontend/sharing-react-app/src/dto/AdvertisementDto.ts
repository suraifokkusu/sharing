// src/dto/AdvertisementDto.ts

// Описание интерфейса для DTO объявления
export interface AdvertisementDto {
  adId: number | null; // Идентификатор объявления, может быть null для новых объявлений
  userId: number; // Идентификатор пользователя, создавшего объявление
  category: string; // Категория объявления
  description: string; // Описание объявления
  adType: string; // Тип объявления ('offer' или 'request')
  status: string; // Статус объявления ('active', 'completed', 'cancelled')
  sessions: SessionDto[]; // Список сессий
  adSkills: AdSkillDto[]; // Список навыков объявления
}

// Описание интерфейса для DTO сессии
export interface SessionDto {
  sessionId: number;
  adId: number;
  sessionDate: string;
  platform: string;
}

// Описание интерфейса для DTO навыка объявления
export interface AdSkillDto {
  skillId: number;
  skillName: string;
}
