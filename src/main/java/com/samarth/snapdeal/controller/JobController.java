package com.samarth.snapdeal.controller;

import com.samarth.snapdeal.model.User;
import com.samarth.snapdeal.service.SnapdealDataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    private final SnapdealDataStore dataStore;

    public AuthController(SnapdealDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> payload) {
        try {
            String name = payload.get("name");
            String email = payload.get("email");
            String password = payload.get("password");
            String role = payload.get("role");

            if (name == null || name.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Name is required."));
            }
            if (email == null || email.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Email is required."));
            }
            if (password == null || password.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Password is required."));
            }
            if (role == null || role.isBlank()) {
                role = "job-seeker";
            }

            User user = dataStore.signup(name, email, password, role);
            return ResponseEntity.ok(Map.of("message", "Account created successfully.", "user", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        try {
            String email = payload.get("email");
            String password = payload.get("password");

            if (email == null || email.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Email is required."));
            }
            if (password == null || password.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Password is required."));
            }

            User user = dataStore.login(email, password);
            return ResponseEntity.ok(Map.of("message", "Login successful.", "user", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "Snapdeal API working", "message", "Welcome to Snapdeal"));
    }
}
