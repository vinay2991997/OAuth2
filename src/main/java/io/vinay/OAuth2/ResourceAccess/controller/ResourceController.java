package io.vinay.OAuth2.ResourceAccess.controller;

import io.vinay.OAuth2.AccessToken.model.AccessToken;
import io.vinay.OAuth2.AccessToken.model.AccessTokenResponse;
import io.vinay.OAuth2.AccessToken.service.AccessTokenService;
import io.vinay.OAuth2.AuthCode.model.AuthData;
import io.vinay.OAuth2.AuthCode.service.AuthDataService;
import io.vinay.OAuth2.Client.model.ClientAuthCredentials;
import io.vinay.OAuth2.Client.service.ClientService;
import io.vinay.OAuth2.Helper.GenerateToken;
import io.vinay.OAuth2.ResourceAccess.model.ResourceUser;
import io.vinay.OAuth2.annotations.acl.Acl;
import io.vinay.OAuth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {

    private ClientService clientService;
    private AuthDataService authDataService;
    private AccessTokenService accessTokenService;
    private UserService userService;

    @Autowired
    public ResourceController(ClientService clientService, AuthDataService authDataService, AccessTokenService accessTokenService, UserService userService) {
        this.clientService = clientService;
        this.authDataService = authDataService;
        this.accessTokenService = accessTokenService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/requestAccessToken")
    public AccessTokenResponse requestAccessToken(@RequestHeader(value = "Authorization") String authorization,
                                                  @RequestHeader(value = "auth_code") String authcode) {

        String initial_clientId = clientService.getClientId(authorization);
        String intital_clientSecret = clientService.getClientSecret(authorization);
        ClientAuthCredentials clientAuthCredentials = new ClientAuthCredentials(initial_clientId,intital_clientSecret,authcode);

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

    @Acl(permission = "read resource")
    @RequestMapping(method = RequestMethod.GET, value = "/requestResource")
    public ResourceUser requestAccessToken(@RequestHeader(value = "access_token") String token) {

        // check for existence
        if (!accessTokenService.existById(token)) {
            return new ResourceUser("Access token is invalid");
        }

        // check for expireTime
        AccessToken accessToken = accessTokenService.getById(token);
        if (accessToken.getExpires_at() < System.currentTimeMillis()) {
            return new ResourceUser("Token has been expired!");
        }

        // provide the user resouces to the client
        String phone = accessToken.getPhone();
        return new ResourceUser(userService.get(phone),"Resources provided Successfully");
    }

}
