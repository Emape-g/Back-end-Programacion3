package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Producto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagenUrl;
}
