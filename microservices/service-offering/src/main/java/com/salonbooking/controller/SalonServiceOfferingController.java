package com.salonbooking.controller;

import com.salonbooking.dto.CategoryDTO;
import com.salonbooking.dto.SalonDTO;
import com.salonbooking.dto.ServiceDTO;
import com.salonbooking.modal.ServiceOffering;
import com.salonbooking.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
           @RequestBody ServiceDTO serviceDTO

    ){
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());

        ServiceOffering serviceOfferings=serviceOfferingService
                .createService(salonDTO,serviceDTO,categoryDTO);
        return ResponseEntity.ok(serviceOfferings);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering

    ) throws Exception {

        ServiceOffering serviceOfferings=serviceOfferingService
                .updateService(id,serviceOffering);
        return ResponseEntity.ok(serviceOfferings);
    }
}
