package io.vinay.OAuth2.ResourceAccess.controller;

import io.vinay.OAuth2.AccessToken.model.AccessToken;
import io.vinay.OAuth2.AccessToken.model.AccessTokenInput;
import io.vinay.OAuth2.AccessToken.model.AccessTokenResponse;
import io.vinay.OAuth2.AccessToken.service.AccessTokenService;
import io.vinay.OAuth2.AuthCode.model.AuthData;
import io.vinay.OAuth2.AuthCode.service.AuthDataService;
import io.vinay.OAuth2.Client.model.ClientAuthCredentials;
import io.vinay.OAuth2.Client.service.ClientService;
import io.vinay.OAuth2.GenerateToken.GenerateToken;
import io.vinay.OAuth2.ResourceAccess.model.ResourceUser;
import io.vinay.OAuth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthDataService authDataService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/requestAccessToken")
    public AccessTokenResponse requestAccessToken(@RequestBody ClientAuthCredentials clientAuthCredentials) {

        String authCode = clientAuthCredentials.getAuthCode();
        String clientId = clientAuthCredentials.getClientId();
        String clientSecret = clientAuthCredentials.getClientSecret();

        // validate credentials
        if (!clientService.existById(clientId)) {
            return new AccessTokenResponse("unknown Client");
        }

        if (!clientService.get(clientId).getClientSecret().equals(clientSecret)) {
            return new AccessTokenResponse("client Secret doesn't match to clientId");
        }

        if (!authDataService.existById(authCode)) {
            return new AccessTokenResponse("Illegal authCode");
        }

        AuthData authData = authDataService.get(authCode);
        if (!authData.getClientId().equals(clientId)) {
            return new AccessTokenResponse("this authCode is not issued to this Client");
        }

        // return Access token
        AccessToken accessToken = GenerateToken.generateAccessToken(authData.getPhone());
        accessTokenService.add(accessToken);

        return new AccessTokenResponse(accessToken.getAccessToken(),"Access Token Successfully Generated!");

    }

    @RequestMapping(method = RequestMethod.POST, value = "/requestResource")
    public ResourceUser requestAccessToken(@RequestBody AccessTokenInput accessTokenInput) {

        // check for existence
        if (!accessTokenService.existById(accessTokenInput.getAccessToken())) {
            return new ResourceUser("Access token is invalid");
        }

        // check for expireTime
        AccessToken accessToken = accessTokenService.getById(accessTokenInput.getAccessToken());
        if (accessToken.getExpires_at() < System.currentTimeMillis()) {
            return new ResourceUser("Token has been expired!");
        }

        // provide the user resouces to the client
        String phone = accessToken.getPhone();
        return new ResourceUser(userService.get(phone),"Resources provided Successfully");
    }

}
