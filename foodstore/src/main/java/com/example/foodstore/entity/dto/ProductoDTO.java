package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Categoria;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductoDTO {
    Long id;
    String nombre;
    double precio;
    Categoria categoria;
    String descripcion;
    String url_imagen;
    int stock;
}
