package io.vinay.OAuth2.SignUp.model;

public class SignUpTokenId {
    private String signUpTokenId;
    private String message;

    public SignUpTokenId() {
        this.signUpTokenId = "------";
        this.message = "Oops.... Account already Existed with these credentials!!";
    }

    public SignUpTokenId(String message) {
        this.signUpTokenId = "------";
        this.message = message;
    }

    public SignUpTokenId(String signUpTokenId, String message) {
        this.signUpTokenId = signUpTokenId;
        this.message = message;
    }

    public String getSignUpTokenId() {
        return signUpTokenId;
    }

    public void setSignUpTokenId(String signUpTokenId) {
        this.signUpTokenId = signUpTokenId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
