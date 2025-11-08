package com.example.foodstore.entity.mapper;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Usuario;
import com.example.foodstore.entity.dto.PedidoCreateDTO;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoCreateMapper {

    @Autowired
    private DetallePedidoCreateMapper detallePedidoCreateMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pedido toEntity(PedidoCreateDTO dto) {
        Pedido pedido = new Pedido();

        Usuario usuario = usuarioRepository.findById(dto.getUsuario_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado con ID: " + dto.getUsuario_id()));

        pedido.setUsuario(usuario);

        pedido.setTelefono(dto.getTelefono());
        pedido.setDireccion(dto.getDireccion());
        pedido.setMetodoPago(dto.getMetodoPago());
        pedido.setNotas(dto.getNotas());
        // Mapeo de los detalles
        List<DetallePedido> detalles = dto.getDetallePedidos()
                .stream()
                .map(detDto -> {
                    DetallePedido detalle = detallePedidoCreateMapper.toEntity(detDto);
                    detalle.setPedido(pedido); // importante mantener la referencia bidireccional
                    return detalle;
                })
                .collect(Collectors.toList());

        pedido.setDetallepedidos(detalles);
        return pedido;
    }
}
