package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario entity){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setApellido(entity.getApellido());
        dto.setCelular(entity.getCelular());
        dto.setRol(entity.getRol());
        dto.setContrasena(entity.getContrasena());
        if(entity.getPedidos() != null){
            dto.setPedidos(entity.getPedidos());
        }
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto){
        Usuario entity = new Usuario();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setApellido(dto.getApellido());
        entity.setCelular(dto.getCelular());
        entity.setRol(dto.getRol());
        entity.setContrasena(dto.getContrasena());
        if(dto.getPedidos() != null){
            entity.setPedidos(dto.getPedidos());
        }
        return entity;
    }
}
