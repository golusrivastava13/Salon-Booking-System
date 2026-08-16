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
import CustomerRoutes from './Routes/CustomerRoutes';

function App() {
  return (
    <ThemeProvider theme={blueTheme}>
      

       <Routes>
        <Route path='/salon-dashboard/*' element={<SalonDashboard/>}/>
        <Route path='*' element={<CustomerRoutes/>}/>
        
       </Routes>
    </ThemeProvider>
   
  );
}

export default App;
