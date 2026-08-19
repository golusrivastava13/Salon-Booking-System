import React from 'react'
import ProfileFildCard from './ProfileFildCard'
import { Divider } from '@mui/material'

const Profile = () => {
  return (
    <div className='lg:px-20 lg:bottom-20 space-y-20'>
       <div className='w-full lg:w-[70%]'>
        <h1 className='text-5xl font-bold pb-5'>Pablo salon</h1>
        <section className='grid grid-cols-2 gap-3'>
            <div className='col-span-2'>
                <img className='w-full rounded-md h-[15rem] object-cover' src="https://images.pexels.com/photos/15461411/pexels-photo-15461411.jpeg" alt="" />
            </div>
            <div className='col-span-1'>
                <img className='w-full rounded-md h-[15rem] object-cover' src="http://res.cloudinary.com/dxoqwusir/image/upload/v1732934203/barber-5497152_1280_zgcao8.jpg" alt="" />
            </div>
            <div className='col-span-1'>
                <img className='w-full rounded-md h-[15rem] object-cover' src="http://res.cloudinary.com/dxoqwusir/image/upload/v1732934217/beauty-salon-4043096_1280_itrjdr.jpg" alt="" />
            </div>
        </section>
       </div>
       <div className='mt-10 lg:w-[70%]'>
        <div className='flex items-center pb-3 justify-between'>
            <h1 className='text-2xl font-bold text-gray-600'>Owner Details</h1>
        </div>
        <div>
            <ProfileFildCard keys={"owner name"} value={"pablo"}/>
            <Divider/>
            <ProfileFildCard keys={"email"} value={"pablo@gmail.com"}/>
            <Divider/>
            <ProfileFildCard keys={"role"} value={"Salon_Owner"}/>
        </div>
       </div>
       <div className='mt-10 lg:w-[70%]'>
        <div className='flex items-center pb-3 justify-between'>
            <h1 className='text-2xl font-bold text-gray-600'>Salon Details</h1>
        </div>
        <div>
            <ProfileFildCard keys={"salon name"} value={"pablo salon"}/>
            <Divider/>
            <ProfileFildCard keys={"salon address"} value={"ambavadi choke, bangalore"}/>
            <Divider/>
            <ProfileFildCard keys={"open time"} value={"10:00 AM"}/>
            <Divider/>
            <ProfileFildCard keys={"close time"} value={"09:00 PM"}/>
        </div>
       </div>
    </div>
  )
}

export default Profile