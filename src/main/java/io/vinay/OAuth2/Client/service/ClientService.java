package io.vinay.OAuth2.Client.service;

import io.vinay.OAuth2.Client.model.Client;
import io.vinay.OAuth2.Client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void add(Client client) {
        clientRepository.save(client);
    }

    public Client get(String clientId) {
        return clientRepository.getOne(clientId);
    }

    public boolean has(String clientId) {
        return clientRepository.existsById(clientId);
    }

    public Client findByName(String appName) {
        return clientRepository.findByAppName(appName);
    }

    public boolean existByName(String appName) {
        return clientRepository.existsByAppName(appName);
    }

    public boolean existById(String clientId) {
        return clientRepository.existsById(clientId);
    }

    public String getClientId(String encoded_auth) {
        String withoutBasic = encoded_auth.substring(6);
        String client = new String(Base64.getDecoder().decode(withoutBasic));
        String clientId = client.substring(0, client.indexOf(':'));
        return clientId;
    }

    public String getClientSecret(String encoded_auth) {
        String withoutBasic = encoded_auth.substring(6);
        String client = new String(Base64.getDecoder().decode(withoutBasic));
        String clientSecret = client.substring(client.indexOf(':') + 1, client.length());
        return clientSecret;
    }
}
