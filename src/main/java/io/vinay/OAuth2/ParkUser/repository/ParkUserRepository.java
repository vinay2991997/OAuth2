package io.vinay.OAuth2.ParkUser.repository;

import io.vinay.OAuth2.ParkUser.model.ParkUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkUserRepository extends JpaRepository<ParkUser, String> {
}
