package io.vinay.OAuth2.permission.controller;

import io.vinay.OAuth2.annotations.acl.Acl;
import io.vinay.OAuth2.permission.model.Permission;
import io.vinay.OAuth2.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Acl(permission = "add permission")
    @RequestMapping(method = RequestMethod.POST, value = "/acl/permission")
    public String addPermission(@RequestBody Permission permission) {
        permissionService.save(permission);
        return "Added Successfully";
    }
}
