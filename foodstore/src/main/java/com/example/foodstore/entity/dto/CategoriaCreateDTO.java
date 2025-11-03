package com.example.foodstore.entity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoriaCreateDTO {
    String nombre;
    String descripcion;
    String imagenUrl;
}
