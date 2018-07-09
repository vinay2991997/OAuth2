package io.vinay.OAuth2.login.controller;

import io.vinay.OAuth2.AuthCode.model.AuthCode;
import io.vinay.OAuth2.AuthCode.model.AuthData;
import io.vinay.OAuth2.AuthCode.service.AuthDataService;
import io.vinay.OAuth2.Client.service.ClientService;
import io.vinay.OAuth2.Helper.GenerateToken;
import io.vinay.OAuth2.login.model.LoginCredential;
import io.vinay.OAuth2.user.model.User;
import io.vinay.OAuth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// TODO : Decoupling

@RestController
public class LoginController {

    private UserService userService;
    private ClientService clientService;
    private AuthDataService authDataService;
    private AuthData authData;

    @Autowired
    public LoginController(UserService userService, ClientService clientService, AuthDataService authDataService, AuthData authData) {
        this.userService = userService;
        this.clientService = clientService;
        this.authDataService = authDataService;
        this.authData = authData;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public AuthCode login(@RequestHeader(value = "clientId") String clientId,
                          @RequestHeader(value = "phone") String phone,
                          @RequestHeader(value = "pass") String pass) {

        // credential validation

        // Client existance check
        if (!clientService.has(clientId)) {
            return new AuthCode("Unknown Client!!");
        }

        // Account Existance check
        if (!userService.has(phone)) {
            return new AuthCode("Account doesn't exist");
        }


        // password check
        User user = userService.get(phone);
        if (user.getPassword().equals(pass)) {

            // STATUS : "Login Successful";

            // check whether the user already has given access to the client
            if (authDataService.hasAlready(clientId, phone)) {
                return new AuthCode(authDataService.getAuthCode(clientId, phone),
                        "User has already given access to the Application");
            }

            // save the details in the auth table
            String authCode = GenerateToken.generateTokenId();
            authData.setAuthCode(authCode);
            authData.setClientId(clientId);
            authData.setPhone(phone);

            authDataService.add(authData);

            // return the Auth Code
            return new AuthCode(authCode, "Login Successful");
        }

        return new AuthCode("password is wrong!!");
    }
}
