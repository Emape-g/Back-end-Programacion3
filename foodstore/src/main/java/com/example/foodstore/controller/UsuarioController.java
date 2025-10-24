package com.example.foodstore.controller;

import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.AuthDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;
import com.example.foodstore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO){
        try{
            Usuario usuario = usuarioService.login(authDTO);
            return ResponseEntity.ok(usuario);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsuarios(){
        try{
            return ResponseEntity.ok(usuarioService.listar());
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id){
        try {
            UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);
            if(usuarioDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(usuarioDTO);
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioDTO usuarioDTO){
        try{
            UsuarioDTO usuarioCreado = usuarioService.crear(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        try{
            UsuarioDTO usuarioActualizado = usuarioService.actualizar(id, usuarioDTO);
            return ResponseEntity.ok(usuarioDTO);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        try{
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
