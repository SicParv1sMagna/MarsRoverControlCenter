import { useState } from 'react'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css'
import MainPage from './pages/MainPage/MainPage';
import { Map } from './pages/Map/Map';
import Control from './pages/RoverControl/Control';
import Navbar from './shared/Navbar/Navbar';
import NotificationTest from './widgets/Notification/NotificationTest';
import { NewMap } from './pages/Map/NewMap';

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/map" element={<Map />} />
        <Route path="/newmap" element={<NewMap />} />
        <Route path="/control" element={<Control />} />
        <Route path="/notification" element={<NotificationTest />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
