package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDto(Producto entity){
        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setPrecio(entity.getPrecio());
        dto.setDescripcion(entity.getDescripcion());
        dto.setUrl_imagen(entity.getUrl_imagen());
        dto.setStock(entity.getStock());
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public Producto toEntity(ProductoDTO dto){
        Producto entity = new Producto();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setDescripcion(dto.getDescripcion());
        entity.setUrl_imagen(dto.getUrl_imagen());
        entity.setStock(dto.getStock());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }

}
