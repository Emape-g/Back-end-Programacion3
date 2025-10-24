package com.example.foodstore.entity.dto;

import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Rol;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioDTO {
    Long id;
    String nombre;
    String apellido;
    String email;
    int celular;
    String contrasena;
    Rol rol;
    List<Pedido> pedidos;
}
