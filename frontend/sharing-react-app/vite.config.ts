import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import dotenv from 'dotenv';
import svgr from 'vite-plugin-svgr'


dotenv.config();

const port = parseInt(process.env.VITE_PORT || '3000');

// Экспорт конфигурации Vite
export default defineConfig({
  plugins: [svgr(), react()],
  server: {
    port: port
  }
});