package com.example.bonvoyagehotel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public String publicApi() {
        return "Public content";
    }

    @GetMapping("/secured")
    public String securedApi(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminOnly() {
        return "Admin access granted!";
    }
}

