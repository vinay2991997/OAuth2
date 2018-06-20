package io.vinay.OAuth2.ParkUser.service;

import io.vinay.OAuth2.ParkUser.model.ParkUser;
import io.vinay.OAuth2.ParkUser.repository.ParkUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkUserService {

    @Autowired
    private ParkUserRepository parkUserRepository;

    public void add(ParkUser parkUser) {
        parkUserRepository.save(parkUser);
    }

    public ParkUser get(String phone) {
        return parkUserRepository.getOne(phone);
    }

    public void remove(String phone) {
        parkUserRepository.deleteById(phone);
    }
}
