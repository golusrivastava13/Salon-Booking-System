import React from 'react'
import SalonDrawerList from './components/SalonDrawerList'
import Navbar from '../Admin Salon/Navbar'
import BookingTable from './Booking/BookingTable'
import ServiceTable from './Services/ServiceTable'
import TransactionTable from './Transaction/TransactionTable'
import Category from './Category/Category'
import CategoryTable from './Category/CategoryTable'
import CategoryForm from './Category/CategoryForm'
import { Route, Routes } from 'react-router-dom'
import HomePage from './Home/HomePage'
import CreateServiceForm from './Services/CreateServiceForm'
import Notifications from '../Customer/Notification/Notifications'

const SalonDashboard = () => {
  return (
    <div className='min-h-screen'>
      <Navbar DrawerList={SalonDrawerList}/>
      <section className='lg:flex lg:h-[90vh]'>
        <div className='hidden lg:block h-full'>
          <SalonDrawerList/>
        </div>
        <div className='p-10 w-full lg:w-[80%] overflow-y-auto'>
          <Routes>
            <Route path='/' element={<HomePage/>}/>
            <Route path='/services' element={<ServiceTable/>}/>
            <Route path='/add-services' element={<CreateServiceForm/>}/>
            <Route path='/bookings' element={<BookingTable/>}/>
            <Route path='/category' element={<Category/>}/>
            <Route path='/transaction' element={<TransactionTable/>}/>
            <Route path='/notifications' element={<Notifications/>}/>
          </Routes>
          {/* <BookingTable/> */}
          {/* <ServiceTable/> */}
          {/* <TransactionTable/> */}
          {/* <Category/> */}
        </div>
      </section>
    </div>
  )
}

export default SalonDashboard