package com.example.foodstore.entity.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidoCreateDTO {
    private List<DetallePedidoCreateDTO> detallePedidos;
}
