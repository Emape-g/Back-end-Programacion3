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
    Long id;
    int cantidad;
    double subtotal;
    Pedido pedido;
    Producto producto;

}
