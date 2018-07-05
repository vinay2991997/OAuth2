package io.vinay.OAuth2.Helper;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isPhoneValid(String phone) {
        return Pattern.matches("[0-9]{10}", phone);
    }
}
