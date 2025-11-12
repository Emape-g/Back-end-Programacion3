package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.DetallePedidoDTO;
import com.example.foodstore.entity.dto.ProductoCreateDTO;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {

    @Autowired
    ProductoRepository productoRepository;

    public DetallePedidoDTO toDto(DetallePedido entity){
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(entity.getId());
        dto.setProducto_id(entity.getProducto().getId());
        dto.setNombre(entity.getProducto().getNombre());
        dto.setUrl_imagen(entity.getProducto().getUrl_imagen());
        dto.setCantidad(entity.getCantidad());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }


}
