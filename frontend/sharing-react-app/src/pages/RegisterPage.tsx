// src/pages/RegisterPage.tsx
import React from 'react';

const RegisterPage: React.FC = () => (
  <div>
    <h2>Register Page</h2>
    <form>
      <div>username: <input /></div>
      <div>password: <input type='password' /></div>
      <button type="submit">register</button>
    </form>
  </div>
);

export default RegisterPage;
