package com.example.foodstore.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class PasswordUtil {
    //Codigificardor
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Verificar contraseña
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    // Para encriptar antes de guardar en BD
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // Para comparar una contraseña ingresada con la guardada
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public boolean isValidPassword(String password) {
        if (password == null) return false;
        return pattern.matcher(password).matches();
    }
}