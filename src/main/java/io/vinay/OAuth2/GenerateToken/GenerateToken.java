package io.vinay.OAuth2.GenerateToken;

import io.vinay.OAuth2.SignUp.model.SignUpToken;

import java.util.Random;

public class GenerateToken {

    public static SignUpToken generate(String phone) {
        Random random = new Random();
        String tokenId = String.valueOf(random.nextInt(900000000) + 100000000);
        String OTP = String.valueOf(random.nextInt(900000) + 100000);
        final Long SEC = 1000L;
        final Long MIN = 60 * SEC;
        Long time = System.currentTimeMillis() + 2 * MIN;

        return new SignUpToken(tokenId, OTP, time, phone);
    }
}
