package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.service.UserService;
import com.mock_project.mock_project.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isEmpty()) {
                return new ResponseEntity<>((User)null, HttpStatus.NOT_FOUND);
            } else {
                User tempAccount = user.get();
                if (tempAccount.getToken() == null) {
                    return new ResponseEntity<>((User)null, HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
        } catch (ExpiredJwtException ex) {
            return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED); //401
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>((User)null, HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
    }

    @PutMapping
    ResponseEntity<?> updateUserOnline(@RequestBody User user) {
        try {
            User createdAccount = userService.updateUserOnline(user);
            return new ResponseEntity<>(createdAccount, HttpStatus.OK); //200
        } catch (ExpiredJwtException ex) {
            return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED); //401
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>((User)null, HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
    }

    @PutMapping("/logout")
    ResponseEntity<?> logout(@RequestBody Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isEmpty()) {
                return new ResponseEntity<>((User)null, HttpStatus.NOT_FOUND);
            } else {
                User tempAccount = user.get();
                tempAccount.setToken(null);
                userService.updateUserOnline(tempAccount);
                return new ResponseEntity<>("Logout success", HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>((User)null, HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
    }
}
