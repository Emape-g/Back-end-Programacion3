package com.example.foodstore.controller;

import com.example.foodstore.entity.dto.CategoriaCreateDTO;
import com.example.foodstore.entity.dto.CategoriaDTO;
import com.example.foodstore.entity.dto.UsuarioDTO;
import com.example.foodstore.exception.ContrasenaInvalidaException;
import com.example.foodstore.exception.EntidadExistenteException;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<?> getCategorias(){
        try{
            return ResponseEntity.ok(categoriaService.listar());
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoria(@PathVariable Long id){
        try{
            return ResponseEntity.ok(categoriaService.buscarPorId(id));
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createCategoria(@RequestBody CategoriaCreateDTO categoriaCreateDTO){
        try {
            CategoriaDTO nuevaCategoria = categoriaService.crear(categoriaCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
        } catch (EntidadExistenteException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            // Errores inesperados
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CategoriaDTO categoriaDTO){
        try{
            CategoriaDTO categoriaActualizadaDTO = categoriaService.actualizar(id, categoriaDTO);
            return ResponseEntity.ok(categoriaActualizadaDTO);
        } catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            // Errores inesperados
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            categoriaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            // Errores inesperados
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }
}
