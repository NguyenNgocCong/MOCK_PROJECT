package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.repository.UserRepository;
import com.mock_project.mock_project.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUserOnline() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<User> usersListOnline = userList.stream().filter(u -> u.getToken() != null).collect(Collectors.toList());
        return usersListOnline;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUserOnline(User user) {
        if (user.getToken() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            boolean existedUser = userRepository.existsById(user.getPureId());
            if (existedUser) {
                return userRepository.save(user);
            } else {
                throw new RuntimeException("Not found user");
            }
        }
        throw new RuntimeException("User's offline, can't update");
    }
}
