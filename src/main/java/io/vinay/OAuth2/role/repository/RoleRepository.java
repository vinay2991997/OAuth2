package io.vinay.OAuth2.role.repository;

import io.vinay.OAuth2.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

}
