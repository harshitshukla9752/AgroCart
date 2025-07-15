package com.agrocart.web.controller;

import com.agrocart.web.config.JwtUtil;
import com.agrocart.web.dto.*;
import com.agrocart.web.model.Address;
import com.agrocart.web.model.User;
import com.agrocart.web.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // -------------------- Register --------------------
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody UserRegisterDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        userService.register(user);

        Optional<User> registeredUser = userService.findByEmail(user.getEmail());
        if (registeredUser.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        String token = jwtUtil.generateToken(registeredUser.get());
        return ResponseEntity.ok(new LoginResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                token
        ));
    }

    // -------------------- Login --------------------
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO dto) {
        Optional<User> userOpt = userService.findByEmail(dto.getEmail());
        if (userOpt.isEmpty() || !userService.matchesPassword(dto.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(401).build();
        }

        User user = userOpt.get();
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                token
        ));
    }

    // -------------------- Get Profile --------------------
    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile() {
        User user = userService.getCurrentUser();
        if (user == null) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(new ProfileDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress()
        ));
    }

    // -------------------- Update Profile --------------------
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody UserUpdateDTO dto) {
        User user = userService.getCurrentUser();
        if (user == null) return ResponseEntity.status(401).body("Unauthorized");

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());

        if (dto.getAddress() != null) {
            AddressDTO a = dto.getAddress();
            user.setAddress(new Address(
                    a.getStreet(),
                    a.getCity(),
                    a.getState(),
                    a.getZipCode(),
                    a.getCountry()
            ));
        }

        userService.save(user);
        return ResponseEntity.ok("Profile updated");
    }

    // -------------------- Logout --------------------
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // JWT is stateless; logout is handled on the client side by deleting the token.
        return ResponseEntity.ok("Logged out successfully");
    }
}
