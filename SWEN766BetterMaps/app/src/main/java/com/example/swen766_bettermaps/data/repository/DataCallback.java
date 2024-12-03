package com.example.swen766_bettermaps.data.repository;

public interface DataCallback<T> {
    void onDataReceived(T data);
    void onError(Exception e);
}
