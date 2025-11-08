package com.example.foodstore.Impl;

import com.example.foodstore.entity.Rol;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.AuthDTO;
import com.example.foodstore.entity.dto.UsuarioCreateDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;
import com.example.foodstore.entity.mapper.AuthMapper;
import com.example.foodstore.entity.mapper.UsuarioCreateMapper;
import com.example.foodstore.entity.mapper.UsuarioMapper;
import com.example.foodstore.exception.ContrasenaInvalidaException;
import com.example.foodstore.exception.EntidadExistenteException;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.UsuarioRepository;
import com.example.foodstore.service.UsuarioService;
import com.example.foodstore.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
    private UsuarioCreateMapper usuarioCreateMapper;

    @Autowired
    private PasswordUtil hash;

    @Override
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado"));;
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioDTO crear(UsuarioCreateDTO usuarioCreateDTO) {
        if (usuarioRepository.findByEmail(usuarioCreateDTO.getEmail()).isPresent()) {
            throw new EntidadExistenteException("El usuario ya existe");
        }
        Usuario usuario = usuarioCreateMapper.toEntity(usuarioCreateDTO);

        if (!hash.isValidPassword(usuarioCreateDTO.getContrasena())) {
            throw new ContrasenaInvalidaException("La constraseña no cumple con los requisitos");
        }
        usuario.setRol(Rol.USUARIO);
        usuario.setContrasena(hash.encode(usuarioCreateDTO.getContrasena()));
        usuario.setPedidos(new ArrayList<>());
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setApellido(dto.getApellido());
        usuario.setCelular(dto.getCelular());
        usuario.setRol(dto.getRol());

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(guardado);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado"));;
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioDTO login(AuthDTO authDTO) {
        Usuario usuario = usuarioRepository.findByEmail(authDTO.getEmail())
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado"));

        if (!hash.matches(authDTO.getContrasena(),usuario.getContrasena() )){
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuarioMapper.toDTO(usuario);
    }
}