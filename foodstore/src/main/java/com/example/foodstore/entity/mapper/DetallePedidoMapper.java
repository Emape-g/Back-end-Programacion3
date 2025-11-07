package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.DetallePedidoDTO;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.DetallePedidoRepository;
import com.example.foodstore.repository.PedidoRepository;
import com.example.foodstore.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProductoRepository productoRepository;

    public DetallePedidoDTO toDto(DetallePedido entity){
        return new DetallePedidoDTO(
                entity.getId(),
                entity.getCantidad(),
                entity.getSubtotal(),
                entity.getPedido().getId(),
                entity.getProducto().getId()
        );
    }

    public DetallePedido toEntity(DetallePedidoDTO dto){
        DetallePedido entity = new DetallePedido();
        entity.setId(dto.getId());
        entity.setCantidad(dto.getCantidad());
        entity.setSubtotal(dto.getSubtotal());
        Pedido pedido = pedidoRepository.findById(dto.getPedido_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Pedido no encontrado"));
        Producto producto = productoRepository.findById(dto.getProducto_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Producto no encontrado"));
        entity.setProducto(producto);
        entity.setPedido(pedido);
        return entity;
    }
}
