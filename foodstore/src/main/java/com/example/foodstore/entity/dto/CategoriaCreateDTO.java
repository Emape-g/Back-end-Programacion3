package com.example.foodstore.entity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoriaCreateDTO {
    private String nombre;
    private String descripcion;
    private String imagenUrl;
}
