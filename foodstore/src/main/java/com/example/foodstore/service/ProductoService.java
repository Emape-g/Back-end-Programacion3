package com.example.foodstore.service;

import com.example.foodstore.entity.dto.CategoriaCreateDTO;
import com.example.foodstore.entity.dto.CategoriaDTO;
import com.example.foodstore.entity.dto.ProductoCreateDTO;
import com.example.foodstore.entity.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    ProductoDTO crear(ProductoCreateDTO productoCreateDTO);
    List<ProductoDTO> listar();
    ProductoDTO buscarPorId(Long id);
    ProductoDTO actualizar(Long id, ProductoCreateDTO productoCreateDTO);
    void eliminar(Long id);
}
