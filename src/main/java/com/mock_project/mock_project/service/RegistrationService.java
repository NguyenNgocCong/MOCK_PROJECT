package com.mock_project.mock_project.service;

import com.mock_project.mock_project.dto.RegistrationDTO;
import com.mock_project.mock_project.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface RegistrationService {
    void registration(RegistrationDTO registrationDTO);

    void register(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
}
