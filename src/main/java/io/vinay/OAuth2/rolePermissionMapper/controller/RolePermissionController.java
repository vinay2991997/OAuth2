package io.vinay.OAuth2.rolePermissionMapper.controller;

import io.vinay.OAuth2.annotations.acl.Acl;
import io.vinay.OAuth2.rolePermissionMapper.model.RolePermission;
import io.vinay.OAuth2.rolePermissionMapper.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Acl(permission = "add role permission")
    @RequestMapping(method = RequestMethod.POST, value = "/acl/rolepermission")
    public String addRolePermission(@RequestBody RolePermission rolePermission) {
        rolePermissionService.save(rolePermission);
        return "Added Successfully";
    }
}
