package com.example.swen766_bettermaps.data;

/**
 * This class will hold information for a generic RIT user
 */

public class RITUser {
    private String RIT_id;
    private String first_name;
    private String email;

    public RITUser(String RIT_id, String first_name, String email) {
        this.RIT_id = RIT_id;
        this.first_name = first_name;
        this.email = email;
    }

    public String getRIT_id() {
        return RIT_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }
}
