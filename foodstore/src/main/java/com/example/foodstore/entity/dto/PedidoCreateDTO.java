package com.example.foodstore.entity.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidoCreateDTO {

    private Long usuario_id;

    private String telefono;
    private String direccion;
    private String metodoPago;
    private String notas;

    private List<DetallePedidoCreateDTO> detallePedidos;
}
