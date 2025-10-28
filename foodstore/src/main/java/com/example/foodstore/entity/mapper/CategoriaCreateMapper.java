package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.dto.CategoriaCreateDTO;
import com.example.foodstore.entity.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaCreateMapper {
    public CategoriaCreateDTO toDto(Categoria entity){
        CategoriaCreateDTO dto = new CategoriaCreateDTO();
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    public Categoria toEntity(CategoriaCreateDTO dto){
        Categoria entity = new Categoria();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
