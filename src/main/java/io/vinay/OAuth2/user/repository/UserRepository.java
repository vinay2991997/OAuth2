package io.vinay.OAuth2.user.repository;

import io.vinay.OAuth2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    public boolean existsByEmail(String email);
}
