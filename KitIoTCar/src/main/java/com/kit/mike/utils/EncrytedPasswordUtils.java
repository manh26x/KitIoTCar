package com.kit.mike.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

    public static String encrytedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password="123";
        String encrytedPassword = encrytedPassword(password);

        System.out.println("Device: " + encrytedPassword);
    }
}
