package com.example.foodstore.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthDTO {
    private String email;
    private String contrasena;
}