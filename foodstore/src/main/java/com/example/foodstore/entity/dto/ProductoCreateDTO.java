package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Categoria;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductoCreateDTO {
    private String nombre;
    private double precio;
    private Long categoriaId;
    private String descripcion;
    private String url_imagen;
    private int stock;
}
