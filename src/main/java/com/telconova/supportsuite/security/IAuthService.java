package com.telconova.supportsuite.security;

import com.telconova.supportsuite.DTO.AuthResponse;
import com.telconova.supportsuite.DTO.LoginRequest;

public interface IAuthService {
    AuthResponse login(LoginRequest request, String ipAddress);
}
