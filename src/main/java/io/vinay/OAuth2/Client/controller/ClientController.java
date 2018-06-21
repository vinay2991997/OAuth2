package io.vinay.OAuth2.Client.controller;

import io.vinay.OAuth2.Client.model.Client;
import io.vinay.OAuth2.Client.service.ClientService;
import io.vinay.OAuth2.GenerateToken.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.POST, value = "/registerMyApp")
    public Client registerClient(@RequestBody String appName) {
        if (clientService.existByName(appName)){
            return clientService.findByName(appName);
        }

        Client client = new Client(GenerateToken.generateTokenId(), GenerateToken.generateTokenId(), appName);
        clientService.add(client);
        return client;
    }
}
