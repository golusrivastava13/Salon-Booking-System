package com.salonbooking.controller;

import com.salonbooking.dto.CategoryDTO;
import com.salonbooking.dto.SalonDTO;
import com.salonbooking.dto.ServiceDTO;
import com.salonbooking.modal.ServiceOffering;
import com.salonbooking.service.ServiceOfferingService;
import com.salonbooking.service.client.CategoryFeignClient;
import com.salonbooking.service.client.SalonFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    private final SalonFeignClient salonFeignClient;
    private CategoryFeignClient categoryFeignClient;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
           @RequestBody ServiceDTO serviceDTO,
           @RequestHeader("Authorization") String jwt

    ) throws Exception {
        SalonDTO salonDTO=salonFeignClient.getSalonByOwnerId(jwt).getBody();

        CategoryDTO categoryDTO=categoryFeignClient.getCategoryById(serviceDTO.getCategory()).getBody();


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
