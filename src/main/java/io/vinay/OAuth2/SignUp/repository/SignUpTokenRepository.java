package io.vinay.OAuth2.SignUp.repository;

import io.vinay.OAuth2.SignUp.model.SignUpToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpTokenRepository extends JpaRepository<SignUpToken,String> {
}
