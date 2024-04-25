package com.turkcell.authserver.services.abstracts;

import com.turkcell.authserver.services.dtos.requests.LoginRequest;
import com.turkcell.authserver.services.dtos.requests.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    void login(LoginRequest loginRequest);
}
