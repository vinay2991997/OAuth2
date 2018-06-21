package io.vinay.OAuth2.AccessToken.repository;

import io.vinay.OAuth2.AccessToken.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {
}
