package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.AuthDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    //Entidad a DTO
    public AuthDTO toDTO(Usuario entity){
        AuthDTO dto = new AuthDTO();
        dto.setEmail(entity.getEmail());
        dto.setContrasena(entity.getContrasena());
        return dto;
    }

    //DTO -> Entidad
    public Usuario toEntity(AuthDTO dto){
        Usuario entity = new Usuario();
        entity.setEmail(dto.getEmail());
        entity.setContrasena(dto.getContrasena());
        return entity;
    }
}
