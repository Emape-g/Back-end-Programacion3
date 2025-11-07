package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Producto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetallePedidoCreateDTO {
    int cantidad;
    double subtotal;
    Long pedido_id;
    Long producto_id;
}
