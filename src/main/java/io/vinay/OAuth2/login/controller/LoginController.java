package io.vinay.OAuth2.login.controller;

import io.vinay.OAuth2.AuthCode.model.AuthCode;
import io.vinay.OAuth2.AuthCode.model.AuthData;
import io.vinay.OAuth2.AuthCode.service.AuthDataService;
import io.vinay.OAuth2.Client.service.ClientService;
import io.vinay.OAuth2.GenerateToken.GenerateToken;
import io.vinay.OAuth2.login.model.LoginCredential;
import io.vinay.OAuth2.user.model.User;
import io.vinay.OAuth2.user.repository.UserRepository;
import io.vinay.OAuth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthDataService authDataService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public AuthCode login(@RequestHeader(value = "clientId") String clientId,
                          @RequestHeader(value = "phone") String phone,
                          @RequestHeader(value = "pass") String pass) {


        LoginCredential loginCredential = new LoginCredential(clientId,phone,pass);

        // credential validation

        // Client existance check
        if (!clientService.has(loginCredential.getClientId())) {
            return new AuthCode("Unknown Client!!");
        }

        // Account Existance check
        if (!userService.has(loginCredential.getPhone())) {
            return new AuthCode("Account doesn't exist");
        }


        // password check
        User user = userService.get(loginCredential.getPhone());
        if (user.getPassword().equals(loginCredential.getPassword())) {

            // STATUS : "Login Successful";

            // check whether the user already has given access to the client
            if (authDataService.hasAlready(loginCredential.getClientId(), loginCredential.getPhone())) {
                return new AuthCode(authDataService.getAuthCode(loginCredential.getClientId(), loginCredential.getPhone()),
                        "User has already given access to the Application");
            }

            // save the details in the auth table
            String authCode = GenerateToken.generateTokenId();
            AuthData authData = new AuthData(authCode,
                    loginCredential.getClientId(),
                    loginCredential.getPhone());

            authDataService.add(authData);

            // return the Auth Code
            return new AuthCode(authCode, "Login Successful");
        }

        return new AuthCode("password is wrong!!");
    }
}
