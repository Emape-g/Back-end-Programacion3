package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.DetallePedidoCreateDTO;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoCreateMapper {

    @Autowired
    ProductoRepository productoRepository;

    public DetallePedido toEntity(DetallePedidoCreateDTO dto){
        DetallePedido entity = new DetallePedido();
        entity.setCantidad(dto.getCantidad());
        entity.setSubtotal(0);
        Producto producto = productoRepository.findById(dto.getProducto_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Producto no encontrado"));
        entity.setProducto(producto);
        return entity;
    }
}