package io.vinay.OAuth2.AuthCode.service;

import io.vinay.OAuth2.AuthCode.model.AuthData;
import io.vinay.OAuth2.AuthCode.repository.AuthDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthDataService {

    @Autowired
    private AuthDataRepository authDataRepository;

    public void add(AuthData authData) {
        authDataRepository.save(authData);
    }

    public List<AuthData> getList(String phone) {
        return authDataRepository.findAllByPhone(phone);
    }

    public boolean hasAlready(String clientId, String phone) {
        List<AuthData> authDataList = getList(phone);
        return (authDataList
                .stream()
                .filter(t -> t.getClientId().equals(clientId))
                .count() != 0);
    }

    public String getAuthCode(String clientId, String phone) {
        List<AuthData> authDataList = getList(phone);
        return (authDataList
                .stream()
                .filter(t -> t.getClientId().equals(clientId))
                .findFirst()
                .get()
                .getAuthCode());
    }

    public boolean existById(String authCode) {
        return authDataRepository.existsById(authCode);
    }

    public AuthData get(String authCode) {
        return authDataRepository.getOne(authCode);
    }
}
