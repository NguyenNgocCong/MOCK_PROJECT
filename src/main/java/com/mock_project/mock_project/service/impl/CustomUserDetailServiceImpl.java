package com.mock_project.mock_project.service.impl;


import com.mock_project.mock_project.dto.impl.CustomUserDetailImpl;
import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User userEntity = user.get();
        return new CustomUserDetailImpl(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoles());
    }

    
}
