import React, { useEffect, useState } from 'react';
import { getAllReviews, createReview, updateReview, deleteReview } from '../services/reviewService';
import { ReviewDto } from '../dto/ReviewDto';

const ReviewsPage: React.FC = () => {
  const [reviews, setReviews] = useState<ReviewDto[]>([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const data = await getAllReviews();
        setReviews(data);
      } catch (err) {
        setError('Failed to fetch reviews.');
      }
    };

    fetchReviews();
  }, []);

  const handleDelete = async (id: number | null) => {
    if (id === null) return;
    try {
      await deleteReview(id);
      setReviews(reviews.filter(review => review.reviewId !== id));
    } catch (err) {
      setError('Failed to delete review.');
    }
  };

  return (
    <div>
      <h2>Reviews Page</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <ul>
        {reviews.map(review => (
          <li key={review.reviewId}>
            <p>Rating: {review.rating}</p>
            <p>Comment: {review.comment}</p>
            <button onClick={() => handleDelete(review.reviewId)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ReviewsPage;
