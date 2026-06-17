package com.andoni.auth.services;

import com.andoni.auth.dto.LoginRequest;
import com.andoni.auth.dto.TokenResponse;

public interface AuthService {

    TokenResponse autenticar(LoginRequest request) throws Exception;
}
