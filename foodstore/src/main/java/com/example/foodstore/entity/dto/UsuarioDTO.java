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
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private Rol rol;
    private List<PedidoDTO> pedidos;
}
