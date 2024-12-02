package com.example.swen766_bettermaps.data.repository.exceptions;

public class LocationAlreadyExistsException extends RuntimeException {
    public LocationAlreadyExistsException(String message) {
        super(message);
    }
}
