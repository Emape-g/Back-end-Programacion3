package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.UsuarioCreateDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCreateMapper {

    public UsuarioCreateDTO toDTO(Usuario entity){
        UsuarioCreateDTO dto = new UsuarioCreateDTO();
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setApellido(entity.getApellido());
        dto.setCelular(entity.getCelular());
        return dto;
    }

    public Usuario toEntity(UsuarioCreateDTO dto){
        Usuario entity = new Usuario();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setApellido(dto.getApellido());
        entity.setCelular(dto.getCelular());

        return entity;
    }

}
