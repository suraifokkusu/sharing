// src/components/AdvertisementList.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { AdvertisementDto } from '../dto/AdvertisementDto'; // Импортируем AdvertisementDto
import { Link } from 'react-router-dom';

const AdvertisementList: React.FC = () => {
  const [ads, setAds] = useState<AdvertisementDto[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchAds = async () => {
      try {
        const response = await axios.get<AdvertisementDto[]>('http://localhost:8080/api/advertisements');
        setAds(response.data);
      } catch (error) {
        setError('Error fetching advertisements');
        console.error('Error fetching advertisements:', error);
      }
    };
    fetchAds();
  }, []);

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Advertisement List</h2>
      <ul>
        {ads.map(ad => (
          <li key={ad.adId}>
            {ad.category} - {ad.description}
            <Link to={`/ads/${ad.adId}`}>Details</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdvertisementList;
