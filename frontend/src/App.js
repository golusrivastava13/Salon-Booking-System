import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import blueTheme from './theme/blueTheme';
import Home from './Customer/Home/Home';
import SalonDetails from './Customer/salon/Salon Details/SalonDetails';
import Bookings from './Customer/Booking/Bookings';
import Notifications from './Customer/Notification/Notifications';
import Navbar from './Customer/Navbar/Navbar';
import { Route, Routes } from 'react-router-dom';
import SalonDashboard from './Salon/SalonDashboard';

function App() {
  return (
    <ThemeProvider theme={blueTheme}>
      <Navbar/>
       {/* <Home/> */}
       {/* <SalonDetails/> */}
       {/* <Bookings/> */}
       {/* <Notifications/> */}

       <Routes>
        <Route path='/salon-dashboard/*' element={<SalonDashboard/>}/>
        <Route path='/' element={<Home/>}/>
        <Route path='/notifications' element={<Notifications/>}/>
        <Route path='/bookings' element={<Bookings/>}/>
        <Route path='/salon/:id' element={<SalonDetails/>}/>
       </Routes>
    </ThemeProvider>
   
  );
}

export default App;
