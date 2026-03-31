package com.tradewisenexus.controller;

import com.tradewisenexus.dto.AuthResponse;
import com.tradewisenexus.dto.LoginRequest;
import com.tradewisenexus.dto.RegisterRequest;
import com.tradewisenexus.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
