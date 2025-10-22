package com.example.foodstore.entity;

import jakarta.persistence.Entity;
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
}
