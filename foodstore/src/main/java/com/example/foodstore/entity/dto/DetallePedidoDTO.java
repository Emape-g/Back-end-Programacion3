package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Producto;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetallePedidoDTO {
    private Long id;
    private Long producto_id;
    private String nombre;
    private int cantidad;
    private double subtotal;
}
