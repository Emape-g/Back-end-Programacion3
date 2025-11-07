package com.example.foodstore.service;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.dto.DetallePedidoCreateDTO;
import com.example.foodstore.entity.dto.DetallePedidoDTO;

import java.util.List;

public interface DetallePedidoService {
    DetallePedidoDTO crear(DetallePedidoCreateDTO detallePedidoCreateDTO);
    List<DetallePedidoDTO> listar();
    DetallePedidoDTO buscarPorId(Long id);
    DetallePedidoDTO actualizar(DetallePedidoDTO dto);
    void eliminar(Long id);


}
