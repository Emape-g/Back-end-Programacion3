package com.example.foodstore.Impl;

import com.example.foodstore.entity.Rol;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.AuthDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;
import com.example.foodstore.entity.mapper.AuthMapper;
import com.example.foodstore.entity.mapper.UsuarioMapper;
import com.example.foodstore.repository.UsuarioRepository;
import com.example.foodstore.service.UsuarioService;
import com.example.foodstore.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private PasswordUtil hash;

    @Override
    public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setRol(Rol.USUARIO);
        usuario.setContrasena(hash.encode(usuarioDTO.getContrasena()));
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    @Override
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setApellido(dto.getApellido());
        usuario.setCelular(dto.getCelular());
        usuario.setRol(dto.getRol());
        usuario.setContrasena(hash.encode(dto.getContrasena()));


        if (dto.getPedidos() != null) {
            usuario.getPedidos().clear();
            usuario.getPedidos().addAll(dto.getPedidos());
        }

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario login(AuthDTO authDTO) {
        Usuario usuario = usuarioRepository.findByEmail(authDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!hash.matches(authDTO.getContrasena(),usuario.getContrasena() )){
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}