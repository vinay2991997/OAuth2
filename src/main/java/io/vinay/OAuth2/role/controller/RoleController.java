package io.vinay.OAuth2.role.controller;

import io.vinay.OAuth2.annotations.acl.Acl;
import io.vinay.OAuth2.role.model.Role;
import io.vinay.OAuth2.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Acl(permission = "add role")
    @RequestMapping(method = RequestMethod.POST, value = "/acl/role")
    public String addRole(@RequestBody Role role) {
        roleService.save(role);
        return "Added Successfully";
    }

}
