package io.vinay.OAuth2.rolePermissionMapper.service;

import io.vinay.OAuth2.permission.model.Permission;
import io.vinay.OAuth2.role.model.Role;
import io.vinay.OAuth2.rolePermissionMapper.model.RolePermission;
import io.vinay.OAuth2.rolePermissionMapper.repository.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rolePermissionService")
public class RolePermissionService {


    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public List<RolePermission> findAllByRoleName(String roleName) {
        return rolePermissionRepository.findAllByRoleName(roleName);
    }

    public void save(RolePermission rolePermission) {
        rolePermissionRepository.save(rolePermission);
    }

    public boolean containsPermission(String roleName, String permission) {

        List<RolePermission> rolePermissionList = findAllByRoleName(roleName);

        for (RolePermission rolepermission : rolePermissionList) {

            if (rolepermission.getPermissionType().equals(permission)) {
                return true;
            }
        }

        return false;
    }
}
