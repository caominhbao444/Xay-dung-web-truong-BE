package com.project.trainingteam.controller.user;

import com.project.trainingteam.config.security.JwtProvider;
import com.project.trainingteam.dto.user.UserDto;
import com.project.trainingteam.service.impl.user.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/token")
    public String getToken(@RequestBody UserDto authRequest) throws Exception {
        // Get user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        if (passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            // Generate token
            return jwtProvider.generateToken(authRequest.getUsername());
        } else {
            throw new Exception("User details invalid.");
        }
    };
}
