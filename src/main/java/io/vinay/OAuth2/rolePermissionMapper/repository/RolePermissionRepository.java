package io.vinay.OAuth2.rolePermissionMapper.repository;

import io.vinay.OAuth2.rolePermissionMapper.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {

    public List<RolePermission> findAllByRoleName(String roleName);
}
