package io.vinay.OAuth2.AuthCode.repository;

import io.vinay.OAuth2.AuthCode.model.AuthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthDataRepository extends JpaRepository<AuthData, String> {

    public List<AuthData> findAllByPhone(String phone);
}
