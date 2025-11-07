package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.DetallePedidoCreateDTO;
import com.example.foodstore.entity.dto.DetallePedidoDTO;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.PedidoRepository;
import com.example.foodstore.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoCreateMapper {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProductoRepository productoRepository;

    public DetallePedidoCreateDTO toDto(DetallePedido entity){
        return new DetallePedidoCreateDTO(
                entity.getCantidad(),
                entity.getSubtotal(),
                entity.getPedido().getId(),
                entity.getProducto().getId()
        );
    }

    public DetallePedido toEntity(DetallePedidoCreateDTO dto){
        DetallePedido entity = new DetallePedido();
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