package io.vinay.OAuth2.permission.repository;


import io.vinay.OAuth2.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
