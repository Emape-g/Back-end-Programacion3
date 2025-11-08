package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.PedidoDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    @Autowired
    private PedidoMapper pedidoMapper;

    public UsuarioDTO toDTO(Usuario entity){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setApellido(entity.getApellido());
        dto.setCelular(entity.getCelular());
        dto.setRol(entity.getRol());

        if(entity.getPedidos() != null){
            List<PedidoDTO> pedidosDtos = entity.getPedidos()
                    .stream()
                    .map(pedidoMapper::toDto)
                    .collect(Collectors.toList());
            dto.setPedidos(pedidosDtos);
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


        return entity;
    }
}
