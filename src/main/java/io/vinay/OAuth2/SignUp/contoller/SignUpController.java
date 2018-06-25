package io.vinay.OAuth2.SignUp.contoller;

import io.vinay.OAuth2.GenerateToken.GenerateToken;
import io.vinay.OAuth2.ParkUser.model.ParkUser;
import io.vinay.OAuth2.ParkUser.service.ParkUserService;
import io.vinay.OAuth2.SignUp.model.SignUpIDOTP;
import io.vinay.OAuth2.SignUp.model.SignUpToken;
import io.vinay.OAuth2.SignUp.model.SignUpTokenId;
import io.vinay.OAuth2.SignUp.service.SignUpTokenService;
import io.vinay.OAuth2.user.model.User;
import io.vinay.OAuth2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParkUserService parkUserService;

    @Autowired
    private SignUpTokenService signUpTokenService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public SignUpTokenId request(@RequestBody User user) {

        if (!userService.isPhoneValid(user.getPhone())) {
            return new SignUpTokenId("Phone number is not valid");
        }


        if (userService.initialCheck(user.getPhone(), user.getEmail())) {

            // send otp
            SignUpToken signUpToken = GenerateToken.generate(user.getPhone());
            System.out.println("OTP : " + signUpToken.getOTP());

            // add to park account
            ParkUser parkUser = new ParkUser(user);
            parkUserService.add(parkUser);

            // return signup token
            signUpTokenService.add(signUpToken);
            return new SignUpTokenId(signUpToken.getTokenId(),"SignUp token generated");
        }

        return new SignUpTokenId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signup/validate")
    public SignUpTokenId validate(@RequestHeader(value = "token") String token,
                                  @RequestHeader(value = "otp") String otp) {

        SignUpIDOTP signUpIDOTP = new SignUpIDOTP(token,otp);
        System.out.println(signUpIDOTP);

        if (!signUpTokenService.has(signUpIDOTP.getTokenId())) {
            return new SignUpTokenId("Invalid signUp token");
        }


        // check for the time
        if (signUpTokenService.getTime(signUpIDOTP.getTokenId()) < System.currentTimeMillis()) {
            return new SignUpTokenId("SignUp token has been Expired!!");
        }

        // check if the otp is valid
        String OTPinDB = signUpTokenService.getOtp(signUpIDOTP.getTokenId());
        String OTPbyUser = signUpIDOTP.getOTP();
        // System.out.println("db : " + OTPinDB);
        // System.out.println("user : " + OTPbyUser);

        if (OTPinDB.equals(OTPbyUser)) {

            // if yes then transfer account from park to main
            String phone = signUpTokenService.getPhone(signUpIDOTP.getTokenId());
            ParkUser parkUser = parkUserService.get(phone);
            User user = new User(parkUser);
            userService.add(user);
            parkUserService.remove(phone);
            signUpTokenService.remove(signUpIDOTP.getTokenId());

            return new SignUpTokenId("Phone number verified and Account Created Successfully!");
        }

        return new SignUpTokenId("Invalid OTP");
    }
}
