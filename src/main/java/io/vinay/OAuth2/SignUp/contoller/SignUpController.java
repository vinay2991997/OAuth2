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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST, value = "/signup/validate")
    public String validate(@RequestBody SignUpIDOTP signUpIDOTP) {

        System.out.println(signUpIDOTP);

        if (!signUpTokenService.has(signUpIDOTP.getTokenId())) {
            return "Invalid signUp token";
        }


        // check for the time
        if (signUpTokenService.getTime(signUpIDOTP.getTokenId()) < System.currentTimeMillis()) {
            return "SignUp token has been Expired!!";
        }

        // check if the otp is valid
        String OTPinDB = signUpTokenService.getOtp(signUpIDOTP.getTokenId());
        String OTPbyUser = signUpIDOTP.getOTP();
        System.out.println("db : " + OTPinDB);
        System.out.println("user : " + OTPbyUser);


        if (OTPinDB.equals(OTPbyUser)) {


            // if yes then transfer account from park to main
            String phone = signUpTokenService.getPhone(signUpIDOTP.getTokenId());
            ParkUser parkUser = parkUserService.get(phone);
            User user = new User(parkUser);
            userService.add(user);
            parkUserService.remove(phone);
            signUpTokenService.remove(signUpIDOTP.getTokenId());

            return "Phone number verified and Account Created Successfully!";
        }

        return "Invalid OTP";
    }
}
