package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.dto.LoginDTO;
import com.mock_project.mock_project.exception.BadCredentialsLoginException;
import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.service.UserService;
import com.mock_project.mock_project.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/login")
@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    public LoginController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            final String token = JwtUtils.generateToken(userDetails.getUsername());
            User user = userService.getByUsername(userDetails.getUsername()).get();
            user.setToken(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (BadCredentialsLoginException ex) {
            return new ResponseEntity<>("You have entered the wrong name and password", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
