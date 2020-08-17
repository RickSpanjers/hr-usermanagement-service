package nl.hrmanagement.usermanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface IPasswordEncoder {
    String encryptPassword(String password);
    boolean validatePassword(String originalPassword, String storedPassword);
}
