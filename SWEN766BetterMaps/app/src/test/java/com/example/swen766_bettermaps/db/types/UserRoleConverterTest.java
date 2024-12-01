package com.example.swen766_bettermaps.db.types;

import static org.junit.Assert.assertEquals;

import com.example.swen766_bettermaps.data.db.types.UserRole;
import com.example.swen766_bettermaps.data.db.types.UserRoleConverter;

import org.junit.Test;

public class UserRoleConverterTest {

    /**
     * Tests that fromUserRole converts a UserRole to a String.
     */
    @Test
    public void testFromUserRole() {
        UserRole role = UserRole.ADMIN;
        String expectedStr = role.name();

        String actualStr = UserRoleConverter.fromUserRole(role);
        assertEquals(expectedStr, actualStr);
    }

    /**
     * Tests that toUserRole converts a valid String to a UserRole.
     */
    @Test
    public void testToUserRole() {
        UserRole role = UserRole.FACULTY;
        String roleStr = UserRoleConverter.fromUserRole(role);

        UserRole roleFromStr = UserRoleConverter.toUserRole(roleStr);
        assertEquals(role, roleFromStr);
    }

}
