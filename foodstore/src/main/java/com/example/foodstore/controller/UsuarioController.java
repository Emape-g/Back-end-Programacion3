package com.example.foodstore.controller;

import com.example.foodstore.entity.dto.AuthDTO;
import com.example.foodstore.entity.dto.UsuarioCreateDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;
import com.example.foodstore.exception.ContrasenaInvalidaException;
import com.example.foodstore.exception.EntidadExistenteException;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
            return ResponseEntity.ok(usuarioDTO);
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO){
        try {
            System.out.println("REGISTER......");
            UsuarioDTO nuevoUsuario = usuarioService.crear(usuarioCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

        } catch (EntidadExistenteException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));

        } catch (ContrasenaInvalidaException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            // Errores inesperados
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        try {
            UsuarioDTO usuarioActualizado = usuarioService.actualizar(id, usuarioDTO);
            return ResponseEntity.ok(usuarioActualizado);
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO){
        try{
            UsuarioDTO usuarioDTO = usuarioService.login(authDTO);
            return ResponseEntity.ok(usuarioDTO);
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
