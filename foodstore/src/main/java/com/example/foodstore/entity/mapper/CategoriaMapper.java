package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper {
    public CategoriaDTO toDto(Categoria entity){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    public Categoria toEntity(CategoriaDTO dto){
        Categoria entity = new Categoria();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
