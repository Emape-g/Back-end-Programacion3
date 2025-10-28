package com.example.foodstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto extends Base{
    private String nombre;
    private double precio;
    private String url_imagen;
    private String descripcion;
    private int stock;
    @ManyToOne
    private Categoria categoria;
}
