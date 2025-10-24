package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.DetallePedido;
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
    LocalDate fecha;
    Estado estado;
    double total;
    List<DetallePedido> detallepedidos = new ArrayList<>();
}
