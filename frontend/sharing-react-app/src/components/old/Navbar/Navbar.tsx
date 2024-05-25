import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css'; // Импортируем стили

const Navbar: React.FC = () => {
  return (
    <nav className="navbar">
      <div className="container">
        <h1>MySite</h1>
        <div>
          <Link to="/">Home</Link>
          <Link to="/about">About</Link>
          <Link to="/contact">Contact</Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
