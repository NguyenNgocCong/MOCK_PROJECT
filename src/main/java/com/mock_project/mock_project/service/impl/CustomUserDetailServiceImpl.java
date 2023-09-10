package com.mock_project.mock_project.service.impl;


import com.mock_project.mock_project.dto.CustomUserDetail;
import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.repository.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User userEntity = user.get();
//        return new CustomUserDetail(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoles());
        return null;
    }
}
