package com.salonbooking.payload.dto;

import kotlin.collections.ArrayDeque;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String username;
    private Boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
    private List<Credential> credentials=new ArrayDeque<>();

}
