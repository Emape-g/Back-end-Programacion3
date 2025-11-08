package com.example.foodstore.service;

import com.example.foodstore.entity.dto.PedidoCreateDTO;
import com.example.foodstore.entity.dto.PedidoDTO;

import java.util.List;
import java.util.Map;

public interface PedidoService {

    PedidoDTO crear(PedidoCreateDTO pedidoCreateDTO);
    List<PedidoDTO> listar();
    PedidoDTO buscarPorId(Long id);
    List<PedidoDTO> buscarPedidoPorUsuario(Long id);
    PedidoDTO actualizarEstado(Long id, String nuevoEstado);
    void eliminar(Long id);
}