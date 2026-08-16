package com.salonbooking.service;

import com.salonbooking.modal.Salon;
import com.salonbooking.payload.dto.SalonDTO;
import com.salonbooking.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {
     Salon createSalon(SalonDTO salon, UserDTO user);

     Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception;

    List<Salon> getAllSalon();

    Salon getSalonById(Long salonId) throws Exception;

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);
}
