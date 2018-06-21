package io.vinay.OAuth2.AccessToken.service;

import io.vinay.OAuth2.AccessToken.model.AccessToken;
import io.vinay.OAuth2.AccessToken.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public void add(AccessToken accessToken) {
        accessTokenRepository.save(accessToken);
    }

    public AccessToken getById(String accessToken) {
        return accessTokenRepository.getOne(accessToken);
    }

    public boolean existById(String accessToken) {
        return accessTokenRepository.existsById(accessToken);
    }
}
