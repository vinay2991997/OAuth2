package io.vinay.OAuth2;

import io.vinay.OAuth2.rolePermissionMapper.service.RolePermissionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RolePermissionTests {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Test
    public void test1() {
        String roleName = "admin";
        String permission = "write resource";

        Assert.assertEquals(rolePermissionService.containsPermission(roleName, permission), true);

    }

    @Test
    public void test2() {
        String roleName = "user";
        String permission = "write resource";

        Assert.assertEquals(rolePermissionService.containsPermission(roleName, permission), false);

    }

    @Test
    public void test3() {
        String roleName = "user";
        String permission = "read resource";

        Assert.assertEquals(rolePermissionService.containsPermission(roleName, permission), true);

    }
}
