package io.vinay.OAuth2.SignUp.service;

import io.vinay.OAuth2.SignUp.model.SignUpToken;
import io.vinay.OAuth2.SignUp.repository.SignUpTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpTokenService {

    @Autowired
    private SignUpTokenRepository signUpTokenRepository;

    public void add(SignUpToken signUpToken) {
        signUpTokenRepository.save(signUpToken);
    }

    public String getOtp(String tokenId) {
        return signUpTokenRepository.getOne(tokenId).getOTP();
    }

    public String getPhone(String tokenId) {
        return signUpTokenRepository.getOne(tokenId).getPhone();
    }

    public Long getTime(String tokenId) {
        return signUpTokenRepository.getOne(tokenId).getTime();
    }

    public void remove(String tokenId) {
        signUpTokenRepository.deleteById(tokenId);
    }

    public boolean has(String tokenId) {
        return signUpTokenRepository.existsById(tokenId);
    }
}
