package com.example.foodstore.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Usuario extends Base{
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String contrasena;
    private Rol rol;
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();
}
