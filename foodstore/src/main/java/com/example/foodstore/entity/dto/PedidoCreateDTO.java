package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Estado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoCreateDTO {
    LocalDate fecha;
    Estado estado;
    double total;
    List<DetallePedidoCreateDTO> detallepedidosCreateDto = new ArrayList<>();
}
