package com.example.foodstore.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthDTO {
    String email;
    String contrasena;
}