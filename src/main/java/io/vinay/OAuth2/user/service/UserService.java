package io.vinay.OAuth2.user.service;

import io.vinay.OAuth2.ParkUser.model.ParkUser;
import io.vinay.OAuth2.user.model.User;
import io.vinay.OAuth2.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }

    public boolean initialCheck(String phone, String email) {
        return !userRepository.existsById(phone) && !userRepository.existsByEmail(email);
    }

    @Deprecated
    public boolean isPhoneValid(String phone) {
        return Pattern.matches("[0-9]{10}", phone);
    }

    public User get(String phone) {
        return userRepository.getOne(phone);
    }

    public boolean has(String phone) {
        return userRepository.existsById(phone);
    }
}
