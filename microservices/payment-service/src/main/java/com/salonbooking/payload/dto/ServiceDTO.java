package com.salonbooking.payload.dto;

import lombok.Data;

@Data
public class ServiceDTO {

    private Long id;


    private String name;

    private String description;

    private int price;

    private int duration;

    private Long salonId;

    private Long category;

    private String image;

    public ServiceDTO(Long id) {
        this.id = id;
    }
}
