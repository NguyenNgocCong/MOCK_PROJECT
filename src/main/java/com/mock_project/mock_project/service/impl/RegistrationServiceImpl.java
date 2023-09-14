package com.mock_project.mock_project.service.impl;


import java.util.Collections;
import java.util.Date;
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
import com.mock_project.mock_project.repository.CartRepository;


import jakarta.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl (UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CartRepository cartRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void registration(RegistrationDTO registrationDTO){

        //Kiểm tra user đã tồn tại hay chưa
        Optional<User> user = userRepository.findByUsername(registrationDTO.getUsername());
        if (user.isPresent()) {
            throw new UserNameExistedException("Username existed!");
        }

        //Kiểm tra role này có trong database hay không
        Optional<Role> role = roleRepository.findByname(registrationDTO.getRole());
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found");
        }

        //Tạo mới một user
        User mappedUser = new User();
        mappedUser.setUsername(registrationDTO.getUsername());
        mappedUser.setEmail(registrationDTO.getEmail());
        mappedUser.setFullname(registrationDTO.getFullName());
        mappedUser.setRoles(Collections.singleton(role.get()));

        //Encode mật khẩu
        mappedUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        //Lưu user xuống database
        userRepository.save(mappedUser);

        // Thêm cart mới cho user vừa tạo
                /* Cart mappedcart = new Cart();
                // mappedcart.setUserId(mappedcart.getUserId());
                // mappedcart.setCreatedDate(mappedcart.getCreatedDate());
                cartRepository.save(mappedcart); */
        Cart newCart = new Cart();
        newCart.setCreatedDate(new Date());
        newCart.setUserId(mappedUser); // Liên kết Cart với User
        cartRepository.save(newCart);
    }
}
