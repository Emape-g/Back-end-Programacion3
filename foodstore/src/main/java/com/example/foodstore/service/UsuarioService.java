package com.example.foodstore.service;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.AuthDTO;
import com.example.foodstore.entity.dto.UsuarioCreateDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO crear(UsuarioCreateDTO usuarioDTO);
    List<UsuarioDTO> listar();
    UsuarioDTO buscarPorId(Long id);
    UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    void eliminar(Long id);

    UsuarioDTO login(AuthDTO authDTO);
}
