// Описание интерфейса для DTO отзыва
export interface ReviewDto {
    reviewId: number | null; // Идентификатор отзыва, может быть null для новых отзывов
    sessionId: number; // Идентификатор сессии
    authorId: number; // Идентификатор автора отзыва
    rating: number; // Рейтинг отзыва
    comment: string; // Комментарий к отзыву
  }
  