package com.example.foodstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Categoria extends Base{
    private String nombre;
    private String descripcion;
    private String imagenUrl;


}
