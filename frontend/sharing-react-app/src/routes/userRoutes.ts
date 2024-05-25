// userRoutes.ts
import express, { Request, Response } from 'express';

// Определяем тип User
type User = {
  userId: string;
  name: string;
  email: string;
  // Другие свойства пользователя
};

const router = express.Router();

router.put('/api/user/profile', async (req: Request, res: Response) => {
  try {
    // Используем тип User
    const { userId, name, email } = req.body as User;

    // Ваша логика обновления профиля пользователя

    res.json({ userId, name, email }); // Возвращаем обновленные данные профиля
  } catch (error) {
    console.error('Error updating profile:', error);
    res.status(500).json({ error: 'Server error' });
  }
});

export default router;
