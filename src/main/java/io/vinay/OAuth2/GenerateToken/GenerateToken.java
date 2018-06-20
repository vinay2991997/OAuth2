package io.vinay.OAuth2.GenerateToken;

import io.vinay.OAuth2.SignUp.model.SignUpToken;

import java.util.Random;

public class GenerateToken {

    private static Random random = new Random();

    // Token Id of length 16 digit
    public static String generateTokenId() {
        String val1 = String.valueOf(random.nextInt(90000000) + 10000000);
        String val2 = String.valueOf(random.nextInt(90000000) + 10000000);
        return val1 + val2;
    }


    // OTP of length 6 digit
    public static String generateOTP() {
        return String.valueOf(random.nextInt(900000) + 100000);
    }


    // Generate a new SignUpToken
    public static SignUpToken generate(String phone) {

        String tokenId = GenerateToken.generateTokenId();
        String OTP = GenerateToken.generateOTP();
        final Long SEC = 1000L;
        final Long MIN = 60 * SEC;
        Long time = System.currentTimeMillis() + 2 * MIN;

        return new SignUpToken(tokenId, OTP, time, phone);
    }
}
