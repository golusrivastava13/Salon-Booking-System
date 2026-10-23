package com.salonbooking.service;

import com.salonbooking.payload.dto.SignupDTO;
import com.salonbooking.payload.response.AuthResponse;

public interface AuthService{
    AuthResponse login(String username, String password) throws Exception;
    AuthResponse signup(SignupDTO req) throws Exception;
    AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception;
}
