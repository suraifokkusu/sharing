// src/components/Navbar.tsx
import React from 'react';
import './Navbar.css';
import Logo from "../../assets/full-logo.svg";

const Navbar: React.FC = () => {
  return (
    <nav className="navbar">
      <a href="" target="_blank">
     <img src={Logo} className="logo" alt="Sharity logo" />
     </a>
      <div className="nav-links">
        <a href="#home">Главная</a>
        <a href="#about">О нас</a>
        <a href="#services">Услуги</a>
        <a href="#contact">Контакты</a>
      </div>
      <div className="buttons">
        <button className="button-registration">Регистрация</button>
        <button className="button-login">Войти</button>
      </div>
    </nav>
  );
};

export default Navbar;
