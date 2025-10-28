package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Categoria;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductoCreateDTO {
    String nombre;
    double precio;
    Long categoriaId;
    String descripcion;
    String url_imagen;
    int stock;
}
