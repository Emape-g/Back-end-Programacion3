package com.example.foodstore.Impl;

import com.example.foodstore.entity.DetallePedido;
import com.example.foodstore.entity.dto.DetallePedidoCreateDTO;
import com.example.foodstore.entity.dto.DetallePedidoDTO;
import com.example.foodstore.entity.mapper.DetallePedidoCreateMapper;
import com.example.foodstore.entity.mapper.DetallePedidoMapper;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.DetallePedidoRepository;
import com.example.foodstore.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Autowired
    DetallePedidoCreateMapper detallePedidoCreateMapper;
    @Autowired
    DetallePedidoMapper detallePedidoMapper;

    @Override
    public DetallePedidoDTO crear(DetallePedidoCreateDTO detallePedidoCreateDTO) {
        DetallePedido detallePedido = detallePedidoCreateMapper.toEntity(detallePedidoCreateDTO);
        detallePedidoRepository.save(detallePedido);
        return detallePedidoMapper.toDto(detallePedido);
    }

    @Override
    public List<DetallePedidoDTO> listar() {
        return detallePedidoRepository.findAll()
                .stream()
                .map(detallePedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetallePedidoDTO buscarPorId(Long id) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("DetallePedido no encontrado"));
        return detallePedidoMapper.toDto(detallePedido);
    }

    @Override
    public DetallePedidoDTO actualizar(DetallePedidoDTO dto) {
        DetallePedido detallePedido = detallePedidoRepository.findById(dto.getId())
                .orElseThrow(()-> new EntidadNoEncontradaException("DetallePedido no encontrado"));
        DetallePedido detalleActualizado = detallePedidoMapper.toEntity(dto);
        detallePedido.setSubtotal(dto.getSubtotal());
        detallePedido.setCantidad(dto.getCantidad());
        detallePedido.setProducto(detalleActualizado.getProducto());
        detallePedido.setPedido(detalleActualizado.getPedido());
        detallePedidoRepository.save(detallePedido);
        return detallePedidoMapper.toDto(detallePedido);
    }

    @Override
    public void eliminar(Long id) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("DetallePedido no encontrado"));
        detallePedidoRepository.deleteById(id);
    }
}
