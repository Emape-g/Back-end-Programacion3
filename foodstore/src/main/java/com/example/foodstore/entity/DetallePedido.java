package com.example.foodstore.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "pedido")
public class DetallePedido extends Base{

    private int cantidad;
    private double subtotal;
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Producto producto;

}
