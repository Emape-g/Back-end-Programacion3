package com.example.foodstore.controller;

import com.example.foodstore.entity.dto.ProductoCreateDTO;
import com.example.foodstore.exception.EntidadExistenteException;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<?> getProductos(){
        try{
            return ResponseEntity.ok(productoService.listar());
        }catch (Exception e) {
            // Errores inesperados
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Long id){
        try{
            return ResponseEntity.ok(productoService.buscarPorId(id));
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

    @PostMapping("/")
    public ResponseEntity<?> createProducto(@RequestBody ProductoCreateDTO productoCreateDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(productoCreateDTO));
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }catch (EntidadExistenteException e) {
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
    public ResponseEntity<?> updateProducto(@PathVariable Long id,@RequestBody ProductoCreateDTO productoCreateDTO){
        try{
            return ResponseEntity.ok().body(productoService.actualizar(id, productoCreateDTO));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        try{
            productoService.eliminar(id);
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
