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
    Long id;
    String nombre;
    List<Producto> productos;
}
