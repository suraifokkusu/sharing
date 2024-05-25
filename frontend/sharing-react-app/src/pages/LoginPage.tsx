import React, { useState } from 'react';
import { GoogleOAuthProvider, GoogleLogin, CredentialResponse } from '@react-oauth/google';
import axios from 'axios';
import config from '../config';
import { useAuth } from '../context/AuthContext';
import { CircularProgress, Typography } from '@mui/material';
import { useSnackbar } from 'notistack';

const LoginPage: React.FC = () => {
  const { login } = useAuth();
  const { enqueueSnackbar } = useSnackbar();
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const handleLoginSuccess = async (response: CredentialResponse) => {
    console.log('Login Success:', response);

    if (response.credential) {
      setLoading(true);
      try {
        const backendResponse = await axios.post(`${config.apiUrl}/api/auth/google`, { token: response.credential });
        console.log('Backend Response:', backendResponse.data);

        if (backendResponse.data.error) {
          setError(backendResponse.data.error);
          enqueueSnackbar('Login failed: ' + backendResponse.data.error, { variant: 'error' });
        } else {
          login(backendResponse.data);
          enqueueSnackbar('Login successful!', { variant: 'success' });
        }
      } catch (error) {
        console.error('Error sending token to backend:', error);
        setError('Error sending token to backend');
        enqueueSnackbar('Error sending token to backend', { variant: 'error' });
      } finally {
        setLoading(false);
      }
    }
  };

  const handleLoginError = () => {
    console.error('Login Failed');
    setError('Login failed');
    enqueueSnackbar('Login failed', { variant: 'error' });
  };

  return (
    <GoogleOAuthProvider clientId={config.clientId}>
      <div>
        <Typography variant="h4" gutterBottom>Login Page</Typography>
        {error && <Typography variant="body1" color="error">{error}</Typography>}
        {loading ? (
          <CircularProgress />
        ) : (
          <GoogleLogin onSuccess={handleLoginSuccess} onError={handleLoginError} />
        )}
      </div>
    </GoogleOAuthProvider>
  );
};

export default LoginPage;
