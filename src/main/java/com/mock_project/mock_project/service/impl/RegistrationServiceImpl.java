package com.mock_project.mock_project.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mock_project.mock_project.dto.RegistrationDTO;
import com.mock_project.mock_project.exception.UserNameExistedException;
import com.mock_project.mock_project.model.Role;
// import com.mock_project.mock_project.model.RoleUser;
import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.repository.UserRepository;
import com.mock_project.mock_project.service.RegistrationService;
import com.mock_project.mock_project.repository.RoleRepository;

import jakarta.mail.MessagingException;
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

        User mappedUser = new User();
        mappedUser.setUsername(registrationDTO.getUsername());
        mappedUser.setEmail(registrationDTO.getEmail());
        mappedUser.setFullname(registrationDTO.getFullname());
        mappedUser.setPassword(registrationDTO.getPassword());
        mappedUser.setRole(Collections.singleton(role.get()));

        mappedUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(mappedUser);
    }


    @Override
    public void register(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

}
