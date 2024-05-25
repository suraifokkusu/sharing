import React, { useEffect, useState } from 'react';
import { Container, Grid, Card, CardContent, Typography, CircularProgress } from '@mui/material';
import { getAllAdvertisements } from '../services/adService';
import { AdvertisementDto } from '../dto/AdvertisementDto';
import { useSnackbar } from 'notistack';

const AdvertisementsPage: React.FC = () => {
  const { enqueueSnackbar } = useSnackbar();
  const [advertisements, setAdvertisements] = useState<AdvertisementDto[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchAdvertisements = async () => {
      try {
        const data = await getAllAdvertisements();
        setAdvertisements(data);
        enqueueSnackbar('Advertisements loaded successfully!', { variant: 'success' });
      } catch (error) {
        console.error('Failed to fetch advertisements:', error);
        enqueueSnackbar('Failed to load advertisements', { variant: 'error' });
      } finally {
        setLoading(false);
      }
    };

    fetchAdvertisements();
  }, [enqueueSnackbar]);

  if (loading) {
    return <CircularProgress />;
  }

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Advertisements</Typography>
      <Grid container spacing={4}>
        {advertisements.map((ad) => (
          <Grid item key={ad.adId} xs={12} sm={6} md={4}>
            <Card>
              <CardContent>
                {/* <Typography variant="h5">{ad.title}</Typography> */}
                <Typography>{ad.description}</Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default AdvertisementsPage;
