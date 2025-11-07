package com.example.foodstore.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioCreateDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String contrasena;
}
