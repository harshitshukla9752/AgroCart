package com.agrocart.web.service;

import com.agrocart.web.exception.ResourceNotFoundException;
import com.agrocart.web.model.User;
import com.agrocart.web.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public Optional<User> getById(String id) {
        return userRepo.findById(id);
    }

    public User update(String id, User updatedProfile) {
        return userRepo.findById(id).map(user -> {
            user.setFirstName(updatedProfile.getFirstName());
            user.setLastName(updatedProfile.getLastName());
            user.setEmail(updatedProfile.getEmail());
            user.setPhone(updatedProfile.getPhone());
            user.setAddress(updatedProfile.getAddress());
            return userRepo.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }
        String email = auth.getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public String register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return "Registered successfully";
    }

    public boolean matchesPassword(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }

    public List<User> getAllUsers() {
    return userRepo.findAll();
    }

    public boolean deleteUser(String id) {
    if (userRepo.existsById(id)) {
        userRepo.deleteById(id);
        return true;
    }
    return false;
    }

    public User updateUserRole(String id, String role) {
    return userRepo.findById(id).map(user -> {
        user.setRole(role);
        return userRepo.save(user);
    }).orElse(null);
    }
}
