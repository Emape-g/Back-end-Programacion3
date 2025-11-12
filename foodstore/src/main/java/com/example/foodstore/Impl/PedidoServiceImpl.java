package com.example.foodstore.Impl;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.Estado;
import com.example.foodstore.entity.Pedido;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.PedidoCreateDTO;
import com.example.foodstore.entity.dto.PedidoDTO;
import com.example.foodstore.entity.mapper.PedidoCreateMapper;
import com.example.foodstore.entity.mapper.PedidoMapper;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.exception.StockInsuficienteException;
import com.example.foodstore.repository.PedidoRepository;
import com.example.foodstore.repository.ProductoRepository;
import com.example.foodstore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoCreateMapper pedidoCreateMapper;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Override
    public PedidoDTO crear(PedidoCreateDTO pedidoCreateDTO) {
        //Crear la entidad Pedido a partir del DTO
        Pedido pedido = pedidoCreateMapper.toEntity(pedidoCreateDTO);

        // Asignamos valores automáticos
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setTotal(0.0);

        double total = 0.0;

        //Procesar cada detalle del pedido
        for (DetallePedido detalle : pedido.getDetallepedidos()) {

            // Buscar el producto y validar existencia
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new EntidadNoEncontradaException(
                            "Producto no encontrado con ID: " + detalle.getProducto().getId()
                    ));

            // Validar stock
            if (producto.getStock() < detalle.getCantidad()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para el producto '" + producto.getNombre() +
                                "'. Disponible: " + producto.getStock() +
                                ", solicitado: " + detalle.getCantidad()
                );
            }

            // Restar stock y persistir cambio
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            // Asignar relaciones y subtotal
            detalle.setProducto(producto);
            detalle.setPedido(pedido);
            detalle.setSubtotal(detalle.getCantidad() * producto.getPrecio());

            total += detalle.getSubtotal();
        }

        //Calcular total y guardar
        pedido.setTotal(total);

        // Guardamos el pedido completo (cascade guarda los detalles)
        Pedido guardado = pedidoRepository.save(pedido);

        // Retornar DTO final
        return pedidoMapper.toDto(guardado);
    }

    @Override
    public List<PedidoDTO> listar() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Pedido no encontrado con ID: " + id));
        return pedidoMapper.toDto(pedido);
    }

    @Override
    public List<PedidoDTO> buscarPedidoPorUsuario(Long id) {
        return pedidoRepository.findByUsuario_id(id)
                .stream()
                .map(pedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Pedido no encontrado con ID: " + id));

        Estado estadoEnum;
        try {
            estadoEnum = Estado.valueOf(nuevoEstado.toUpperCase()); // valida el enum
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado no válido. Usa: PENDIENTE, CONFIRMADO, CANCELADO o TERMINADO");
        }
        if(estadoEnum == Estado.CANCELADO){
            for (DetallePedido detalle : pedido.getDetallepedidos()) {
                // Buscar el producto y validar existencia
                Producto producto = productoRepository.findById(detalle.getProducto().getId())
                        .orElseThrow(() -> new EntidadNoEncontradaException(
                                "Producto no encontrado con ID: " + detalle.getProducto().getId()
                        ));
                // Sumar stock y persistir cambio
                producto.setStock(producto.getStock() + detalle.getCantidad());
                productoRepository.save(producto);
            }
        }
        pedido.setEstado(estadoEnum);
        Pedido guardado = pedidoRepository.save(pedido);
        return pedidoMapper.toDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Pedido no encontrado con ID: " + id));
        pedidoRepository.delete(pedido);
    }


}
