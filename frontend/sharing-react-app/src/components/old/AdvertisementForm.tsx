// src/components/AdvertisementForm.tsx

import React, { useState } from 'react';
import axios from 'axios';
import { AdvertisementDto } from '../dto/AdvertisementDto'; // Импортируем AdvertisementDto

const AdvertisementForm: React.FC = () => {
  const [ad, setAd] = useState<AdvertisementDto>({
    adId: null,
    userId: 1, // Замените на реальный идентификатор пользователя
    category: '',
    description: '',
    adType: 'offer',
    status: 'active'
  });
  const [error, setError] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAd({ ...ad, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/advertisements', ad);
      console.log('Advertisement created:', response.data);
    } catch (error) {
      setError('Error creating advertisement');
      console.error('Error creating advertisement:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Category:</label>
        <input type="text" name="category" value={ad.category} onChange={handleChange} />
      </div>
      <div>
        <label>Description:</label>
        <input type="text" name="description" value={ad.description} onChange={handleChange} />
      </div>
      <div>
        <label>Type:</label>
        <input type="text" name="adType" value={ad.adType} onChange={handleChange} />
      </div>
      <div>
        <label>Status:</label>
        <input type="text" name="status" value={ad.status} onChange={handleChange} />
      </div>
      {error && <div>{error}</div>}
      <button type="submit">Submit</button>
    </form>
  );
};

export default AdvertisementForm;
