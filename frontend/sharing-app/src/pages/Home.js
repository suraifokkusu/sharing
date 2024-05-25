import React from 'react';
import './Home.css';  // Ссылка на ваш CSS файл

function Home() {
  return (
    <div className="home">
      <section className="home-hero">
        <h1>Welcome to Our Sharing Platform</h1>
        <p>Discover a new world of sharing possibilities and enhance your learning experience.</p>
        <button className="cta-button">Get Started</button>
      </section>

      <section className="features">
        <h2>Why Choose Us?</h2>
        <div className="feature-list">
          <div className="feature">
            <h3>Variety of Resources</h3>
            <p>Access a wide range of tools and materials.</p>
          </div>
          <div className="feature">
            <h3>User-Friendly Interface</h3>
            <p>Enjoy an easy-to-navigate platform.</p>
          </div>
          <div className="feature">
            <h3>Community Support</h3>
            <p>Join a community that supports and grows together.</p>
          </div>
        </div>
      </section>

      <section className="testimonials">
        <h2>What Our Users Say</h2>
        <blockquote>
          "This platform has revolutionized the way I learn and share resources with peers."
        </blockquote>
        <p>- Jane Doe</p>
      </section>

      <footer className="home-footer">
        <p>© 2023 Your Platform Name. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default Home;
