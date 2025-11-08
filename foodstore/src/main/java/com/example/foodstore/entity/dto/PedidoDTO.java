package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Estado;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidoDTO {
    private Long id;
    private LocalDate fecha;
    private Estado estado;
    private double total;

    private String telefono;
    private String direccion;
    private String metodoPago;
    private String notas;

    private List<DetallePedidoDTO> detallePedidos;

}
