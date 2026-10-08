package com.salonbooking.controller;

import com.salonbooking.mapper.SalonMapper;
import com.salonbooking.modal.Salon;
import com.salonbooking.payload.dto.SalonDTO;
import com.salonbooking.payload.dto.UserDTO;
import com.salonbooking.service.SalonService;
import com.salonbooking.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;
    private final UserFeignClient userFeignClient;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        Salon salon=salonService.createSalon(salonDTO, userDTO);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);

    }

    @PutMapping("/{salonId}")
    public  ResponseEntity<SalonDTO> updateSalon(@PathVariable Long salonId,
                                                @RequestBody SalonDTO salonDTO,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {


        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        System.out.println("------- "+salonId+"email "+salonDTO.getEmail());

        Salon salon=salonService.updateSalon(salonDTO, userDTO, salonId);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);

    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getSalons(){

        List<Salon> salons=salonService.getAllSalon();

        List<SalonDTO> salonDTOS=salons.stream().map(salon -> {
            SalonDTO salonDTO=SalonMapper.mapToDTO(salon);
            return  salonDTO;
        }).toList();
        return  ResponseEntity.ok(salonDTOS);

    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long salonId) throws Exception {

        Salon salon = salonService.getSalonById(salonId);
        SalonDTO salonDTO=SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);

    }

    //http://localhost:5002/api/salons/search?city=mumbai
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(
            @RequestParam("city") String city
    ){

        List<Salon> salons=salonService.searchSalonByCity(city);

        List<SalonDTO> salonDTOS=salons.stream().map(salon -> {
            SalonDTO salonDTO=SalonMapper.mapToDTO(salon);
            return  salonDTO;
        }).toList();
        return  ResponseEntity.ok(salonDTOS);

    }

    @GetMapping("/owner/{salonId}")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();
        if(userDTO==null){
            throw new Exception("user not found from jwt....");
        }
        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO salonDTO=SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);

    }


}
