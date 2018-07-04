package io.vinay.OAuth2.rolePermissionMapper.repository;

import io.vinay.OAuth2.rolePermissionMapper.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {

    public List<RolePermission> findAllByRoleName(String roleName);
}
