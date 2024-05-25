// AppRouter.tsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import { SnackbarProvider } from 'notistack';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import ProfilePage from './pages/ProfilePage/ProfilePage';
import AdvertisementsPage from './pages/AdvertisementsPage';
import UserList from './components/UserList';
import ZoomMeeting from './components/ZoomMeeting';
import SessionsPage from './pages/SessionsPage';
import ReviewsPage from './pages/ReviewsPage';
import SkillsPage from './pages/SkillsPage';
import NotFoundPage from './pages/NotFoundPage';
import ProtectedRoute from './components/ProtectedRoute';
import { useAuth } from './context/AuthContext';
import EditProfilePage from './pages/EditProfilePage';

const AppRouter: React.FC = () => {
  const { user, logout } = useAuth();

  return (
    <SnackbarProvider maxSnack={3}>
      <Router>
        {/* Навигационная панель */}
        <div>
          <AppBar position="static">
            <Toolbar>
              <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                Sharing Platform
              </Typography>
              <Button color="inherit" component={Link} to="/">Home</Button>
              <Button color="inherit" component={Link} to="/users">Users</Button>
              <Button color="inherit" component={Link} to="/ads">Advertisements</Button>
              <Button color="inherit" component={Link} to="/profile">Profile</Button>
              <Button color="inherit" component={Link} to="/edit-profile">Edit Profile</Button>
              <Button color="inherit" component={Link} to="/zoom">Zoom Meeting</Button>
              <Button color="inherit" component={Link} to="/sessions">Sessions</Button>
              <Button color="inherit" component={Link} to="/reviews">Reviews</Button>
              <Button color="inherit" component={Link} to="/skills">Skills</Button>
              {user ? (
                <>
                  <Typography variant="body1" component="div" sx={{ flexGrow: 1 }}>
                    {user.name} logged in
                  </Typography>
                  <Button color="inherit" onClick={logout}>Logout</Button>
                </>
              ) : (
                <>
                  <Button color="inherit" component={Link} to="/login">Login</Button>
                  <Button color="inherit" component={Link} to="/register">Register</Button>
                </>
              )}
            </Toolbar>
          </AppBar>

          {/* Контейнер для контента страниц */}
          <Container sx={{ mt: 2 }}>
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/ads" element={<AdvertisementsPage />} />
              <Route path="/users" element={<UserList />} />
              <Route path="/profile" element={
                <ProtectedRoute isAuthenticated={!!user}>
                  <ProfilePage user={user} />
                </ProtectedRoute>
              } />
              <Route path="/edit-profile" element={
                <ProtectedRoute isAuthenticated={!!user}>
                  <EditProfilePage />
                </ProtectedRoute>
              } />
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/zoom" element={<ZoomMeeting />} />
              <Route path="/sessions" element={<SessionsPage />} />
              <Route path="/reviews" element={<ReviewsPage />} />
              <Route path="/skills" element={<SkillsPage />} />
              <Route path="*" element={<NotFoundPage />} />
            </Routes>
          </Container>
        </div>
      </Router>
    </SnackbarProvider>
  );
};

export default AppRouter;
