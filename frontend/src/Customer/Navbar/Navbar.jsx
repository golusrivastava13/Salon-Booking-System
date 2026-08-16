import { AccountCircle, NotificationsActive } from '@mui/icons-material'
import { Avatar, Badge, Button, IconButton, Menu, MenuItem } from '@mui/material'
import React from 'react'
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
     const [anchorEl, setAnchorEl] = React.useState(null);
     const navigate=useNavigate();

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  return (
    <div className='z-50 px-6 flex items-center justify-between py-2'>
        <div className='flex items-center gap-10'>
            <h1 onClick={()=>navigate("/")} className='cursor-pointer font-bold text-2xl'>Salon Service</h1>
            <div className='flex items-center gap-5'>
                <h1>Home</h1>
            </div>
        </div>
        <div className='flex items-center gap-3 md:gap-6'>
            <Button variant='outlined'>Become partner</Button>

            <IconButton onClick={()=>navigate("/notifications")}>
                <Badge badgeContent={5}>
                    <NotificationsActive color='primary'/>
                </Badge>
            </IconButton>

            {true?  <div className='flex gap-1 items-center'>
                <h1 className='text-lg font-semibold'>Golu</h1>
                <IconButton aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                    <Avatar sx={{bgcolor:"blue"}}>
                        G
                    </Avatar>
                </IconButton>
                <Menu
        id="simple-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem onClick={()=>{
            navigate("/bookings")
            handleClose()
        }}>My bookings</MenuItem>
        <MenuItem onClick={handleClose}>Logout</MenuItem>
      </Menu>
            </div>
           :<IconButton>
                <AccountCircle sx={{fontSize:"45px", color:"blue"}}/>
            </IconButton>}
        </div>
    </div>
  )
}

export default Navbar