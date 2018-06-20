package io.vinay.OAuth2.SignUp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SignUpToken {

    @Id
    private String tokenId;
    private String OTP;
    private Long time;
    private String phone;

    public SignUpToken() {
        this.tokenId = "";
        this.OTP = "";
        this.time = 0L;
        this.phone = "";
    }

    public SignUpToken(SignUpToken token) {
        this.tokenId = token.getTokenId();
        this.OTP = token.getOTP();
        this.time = token.getTime();
        this.phone = token.getPhone();
    }

    public SignUpToken(String tokenId, String OTP, Long time, String phone) {
        this.tokenId = tokenId;
        this.OTP = OTP;
        this.time = time;
        this.phone = phone;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
