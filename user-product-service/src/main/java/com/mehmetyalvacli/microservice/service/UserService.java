package com.mehmetyalvacli.microservice.service;

import com.mehmetyalvacli.microservice.models.User;
import com.mehmetyalvacli.microservice.payload.response.MessageResponse;
import com.mehmetyalvacli.microservice.repository.UserRepository;
import com.mehmetyalvacli.microservice.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    public Long getUserIdFromToken(String token) {

        String username = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: userId is not found.")).getId();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: userId is not found."));
    }
}
