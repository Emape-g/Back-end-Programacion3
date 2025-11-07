package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.dto.DetallePedidoDTO;
import com.example.foodstore.entity.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    public PedidoDTO toDto(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setFecha(pedido.getFecha());
        dto.setEstado(pedido.getEstado());
        dto.setTotal(pedido.getTotal());

        List<DetallePedidoDTO> detalles = pedido.getDetallepedidos()
                .stream()
                .map(detallePedidoMapper::toDto)
                .collect(Collectors.toList());

        dto.setDetallePedidos(detalles);
        return dto;
    }
}
