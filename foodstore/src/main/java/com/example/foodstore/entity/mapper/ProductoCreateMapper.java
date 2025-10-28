package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.ProductoCreateDTO;
import com.example.foodstore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoCreateMapper {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProductoCreateDTO toDto(Producto entity){
        ProductoCreateDTO dto = new ProductoCreateDTO();
        dto.setNombre(entity.getNombre());
        dto.setPrecio(entity.getPrecio());
        dto.setDescripcion(entity.getDescripcion());
        dto.setUrl_imagen(entity.getUrl_imagen());
        dto.setStock(entity.getStock());
        dto.setCategoriaId(entity.getCategoria().getId());
        return dto;
    }

    public Producto toEntity(ProductoCreateDTO dto){
        Producto entity = new Producto();
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setDescripcion(dto.getDescripcion());
        entity.setUrl_imagen(dto.getUrl_imagen());
        entity.setStock(dto.getStock());
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        entity.setCategoria(categoria);
        return entity;
    }
}
