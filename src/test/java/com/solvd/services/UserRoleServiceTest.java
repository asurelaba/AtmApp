package com.solvd.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import com.solvd.db.model.UserRole;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserRoleServiceTest {

    private UserRoleService ur;

    @BeforeMethod
    public void setUp() {
        ur = new UserRoleService();
    }

    @Test
    public void testGetUserRoleByRoleName() {
        UserRole clientRole = ur.getUserRoleByRoleName("Client");
        UserRole adminRole = ur.getUserRoleByRoleName("Administrator");
        assertNotEquals(clientRole, adminRole);
        assertEquals(clientRole.getName(), "Client");
        assertEquals(adminRole.getName(), "Administrator");
    }

}