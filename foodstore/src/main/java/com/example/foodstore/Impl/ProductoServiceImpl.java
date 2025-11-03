package com.example.foodstore.Impl;

import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.Producto;
import com.example.foodstore.entity.dto.ProductoCreateDTO;
import com.example.foodstore.entity.dto.ProductoDTO;
import com.example.foodstore.entity.mapper.ProductoCreateMapper;
import com.example.foodstore.entity.mapper.ProductoMapper;
import com.example.foodstore.exception.EntidadExistenteException;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.CategoriaRepository;
import com.example.foodstore.repository.ProductoRepository;
import com.example.foodstore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoCreateMapper productoCreateMapper;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ProductoDTO crear(ProductoCreateDTO productoCreateDTO) {
        if(productoRepository.findByNombre(productoCreateDTO.getNombre()).isPresent()){
            throw new EntidadExistenteException("Producto ya existente!");
        }
        Categoria categoria = categoriaRepository.findById(productoCreateDTO.getCategoriaId()).orElseThrow(() -> new EntidadNoEncontradaException("Categoria no encontrado"));
        Producto productoCrear = productoCreateMapper.toEntity(productoCreateDTO);
        Producto productoGuardado = productoRepository.save(productoCrear);
        return productoMapper.toDto(productoGuardado);
    }

    @Override
    public List<ProductoDTO> listar() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO buscarPorId(Long id) {
        Producto producto =  productoRepository.findById(id).orElseThrow(()-> new EntidadNoEncontradaException("Producto no encontrado"));
        return productoMapper.toDto(producto);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoCreateDTO productoCreateDTO){
        Producto producto =  productoRepository.findById(id).orElseThrow(()-> new EntidadNoEncontradaException("Producto no encontrado"));
        Categoria categoria = categoriaRepository.findById(productoCreateDTO.getCategoriaId()).orElseThrow(() -> new EntidadNoEncontradaException("Categoria no encontrado"));
        Producto productoValores = productoCreateMapper.toEntity(productoCreateDTO);
        producto.setNombre(productoValores.getNombre());
        producto.setPrecio(productoValores.getPrecio());
        producto.setDescripcion(productoValores.getDescripcion());
        producto.setUrl_imagen(productoValores.getUrl_imagen());
        producto.setStock(productoValores.getStock());
        producto.setCategoria(categoria);
        return productoMapper.toDto(productoRepository.save(producto));
    }

    @Override
    public void eliminar(Long id) {
        Producto producto =  productoRepository.findById(id).orElseThrow(()-> new EntidadNoEncontradaException("Producto no encontrado"));
        productoRepository.delete(producto);
    }
}
