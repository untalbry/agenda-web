package com.binarybrains.utils;

public enum ErrorCode {
    RN001("RN001", "Parameter required"),
    RN002("RN002", "Invalid format"),
    RN003("RN003", "Entity already exists"),
    RN004("RN004", "Entity does not exists"),
    RN005("RN005", "Invalid credentials"),

    RN006("RN006", "Unexpected error");

    ErrorCode(String rn, String s) {
    }
}

