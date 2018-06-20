package io.vinay.OAuth2.SignUp.model;

public class SignUpIDOTP {
    private String tokenId;
    private String OTP;

    public SignUpIDOTP() {
        this.tokenId = "";
        this.OTP = "";
    }

    public SignUpIDOTP(String tokenId, String OTP) {
        this.tokenId = tokenId;
        this.OTP = OTP;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    @Override
    public String toString() {
        return "SignUpIDOTP{" +
                "tokenId='" + tokenId + '\'' +
                ", OTP='" + OTP + '\'' +
                '}';
    }
}
