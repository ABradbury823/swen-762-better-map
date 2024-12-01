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

}
