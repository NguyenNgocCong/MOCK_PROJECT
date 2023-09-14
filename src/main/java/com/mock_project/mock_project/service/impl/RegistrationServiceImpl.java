package com.mock_project.mock_project.service.impl;


import java.util.Collections;
import java.util.Optional;

import com.mock_project.mock_project.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mock_project.mock_project.dto.RegistrationDTO;
import com.mock_project.mock_project.exception.UserNameExistedException;
import com.mock_project.mock_project.exception.RoleNotFoundException;
import com.mock_project.mock_project.model.Cart;
import com.mock_project.mock_project.model.Role;
// import com.mock_project.mock_project.model.RoleUser;
import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.repository.UserRepository;
import com.mock_project.mock_project.service.RegistrationService;
import com.mock_project.mock_project.repository.RoleRepository;


import jakarta.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl (UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void registration(RegistrationDTO registrationDTO){
        Optional<User> user = userRepository.findByUsername(registrationDTO.getUsername());
        if (user.isPresent()) {
            throw new UserNameExistedException("Username existed!");
        }

        Optional<Role> role = roleRepository.findByname(registrationDTO.getRole());
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found");
        }



        String userName = registrationDTO.getUsername();
        String token = JwtUtils.generateToken(userName);

        if (token != null && !token.isEmpty()) {
            // In giá trị token ra console
            System.out.println("Token: " + token);

            User mappedUser = new User();
            mappedUser.setUsername(registrationDTO.getUsername());
            mappedUser.setEmail(registrationDTO.getEmail());
            mappedUser.setFullname(registrationDTO.getFullName());
            mappedUser.setRoles(Collections.singleton(role.get()));
            mappedUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

            userRepository.save(mappedUser);
        } else {
            // Xử lý lỗi nếu không thể tạo ra token
            System.out.println("Lỗi");
        }
        // Trả về token trong phản hồi HTTP
    }
        //gọi phương thức tạo cart sau khi tạo một user mới

}
