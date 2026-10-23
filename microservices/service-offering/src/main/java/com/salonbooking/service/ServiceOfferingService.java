package com.salonbooking.service;

import com.salonbooking.dto.CategoryDTO;
import com.salonbooking.dto.SalonDTO;
import com.salonbooking.dto.ServiceDTO;
import com.salonbooking.modal.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);
    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;
    Set<ServiceOffering> getAllServiceBySalonId(long salongId, Long categoryId);
    Set<ServiceOffering> getServiceByIds(Set<Long> ids);
    ServiceOffering getServiceById(Long id) throws Exception;
}
