import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Sessions from './pages/Sessions';
import Transactions from './pages/Transactions';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" exact element={<Home/>} />
        <Route path="/sessions" element={<Sessions/>} />
        <Route path="/transactions" element={<Transactions/>} />
      </Routes>
    </Router>
  );
}

export default App;
