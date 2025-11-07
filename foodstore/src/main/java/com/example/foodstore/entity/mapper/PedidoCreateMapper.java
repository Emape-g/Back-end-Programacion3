package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.dto.PedidoCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoCreateMapper {

    @Autowired
    private DetallePedidoCreateMapper detallePedidoCreateMapper;

    public Pedido toEntity(PedidoCreateDTO dto) {
        Pedido pedido = new Pedido();
        // Mapeo de los detalles
        List<DetallePedido> detalles = dto.getDetallePedidos()
                .stream()
                .map(detDto -> {
                    DetallePedido detalle = detallePedidoCreateMapper.toEntity(detDto);
                    detalle.setPedido(pedido); // importante mantener la referencia bidireccional
                    return detalle;
                })
                .collect(Collectors.toList());

        pedido.setDetallepedidos(detalles);
        return pedido;
    }
}
