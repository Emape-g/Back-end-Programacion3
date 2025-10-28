package com.example.foodstore.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioCreateDTO {
    String nombre;
    String apellido;
    String email;
    String celular;
    String contrasena;
}
