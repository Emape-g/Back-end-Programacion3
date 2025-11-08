package com.example.foodstore.controller;

import com.example.foodstore.entity.dto.PedidoCreateDTO;
import com.example.foodstore.entity.dto.ProductoCreateDTO;
import com.example.foodstore.exception.EntidadExistenteException;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.exception.StockInsuficienteException;
import com.example.foodstore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;

    @GetMapping("/")
    public ResponseEntity<?> getProductos(){
        try{
            return ResponseEntity.ok(pedidoService.listar());
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
            return ResponseEntity.ok(pedidoService.buscarPorId(id));
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

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getPedidoPorUsuario(@PathVariable Long id){
        try{
            return ResponseEntity.ok(pedidoService.buscarPedidoPorUsuario(id));
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createProducto(@RequestBody PedidoCreateDTO pedidoCreateDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(pedidoCreateDTO));
        }catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }catch (EntidadExistenteException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));
        }catch (StockInsuficienteException e){
            return ResponseEntity
                    .status(HttpStatus.LOCKED)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            // Errores inesperados
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            String nuevoEstado = body.get("estado"); // 👈 obtiene el valor enviado en el JSON
            return ResponseEntity.ok(pedidoService.actualizarEstado(id, nuevoEstado));
        } catch (EntidadNoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        } catch (IllegalArgumentException e) {
            // por si el estado no es válido
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Estado no válido. Usa: PENDIENTE, CONFIRMADO, CANCELADO o TERMINADO"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno en el servidor"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        try{
            pedidoService.eliminar(id);
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
