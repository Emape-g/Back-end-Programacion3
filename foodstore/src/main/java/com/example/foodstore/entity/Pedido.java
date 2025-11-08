package com.example.foodstore.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido extends Base{

    private LocalDate fecha;
    private Estado estado;
    private double total;

    private String telefono;
    private String direccion;
    private String metodoPago;
    private String notas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallepedidos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
