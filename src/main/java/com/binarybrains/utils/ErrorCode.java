package com.binarybrains.utils;

public enum ErrorCode {
    RN001("RN001", "Parameter required"),
    RN002("RN002", "Invalid format"),
    RN003("RN003", "Entity already exists"),
    RN004("RN004", "Entity does not exists"),
    RN005("RN003", "Invalid credentials");

    ErrorCode(String rn, String s) {
    }
}

