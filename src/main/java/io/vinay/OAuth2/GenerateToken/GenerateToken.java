package io.vinay.OAuth2.GenerateToken;

import io.vinay.OAuth2.AccessToken.model.AccessToken;
import io.vinay.OAuth2.SignUp.model.SignUpToken;

import java.util.Random;

public class GenerateToken {

    private static Random random = new Random();
    private static final Long SECOND = 1000L;
    private static final Long MINUTE = 60 * SECOND;
    private static final Long HOUR = 60 * MINUTE;
    private static final Long DAY = 24 * HOUR;
    private static final Long MONTH = 30 * DAY;
    private static final Long YEAR = 365 * DAY;

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

        Long time = System.currentTimeMillis() + 2 * MINUTE;

        return new SignUpToken(tokenId, OTP, time, phone);
    }

    // Generate a new SignUpToken
    public static AccessToken generateAccessToken(String phone) {

        String tokenId = GenerateToken.generateTokenId();
        Long time = System.currentTimeMillis() + MONTH;

        return new AccessToken(tokenId,time,phone);
    }
}
