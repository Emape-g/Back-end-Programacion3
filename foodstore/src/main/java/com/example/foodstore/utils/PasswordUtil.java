package com.example.foodstore.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Para encriptar antes de guardar en BD
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // Para comparar una contraseña ingresada con la guardada
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}