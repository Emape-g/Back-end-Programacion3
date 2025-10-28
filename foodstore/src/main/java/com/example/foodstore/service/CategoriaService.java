package com.example.foodstore.service;

import com.example.foodstore.entity.dto.CategoriaCreateDTO;
import com.example.foodstore.entity.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
     CategoriaDTO crear(CategoriaCreateDTO categoriaCreateDTO);
     List<CategoriaDTO> listar();
     CategoriaDTO buscarPorId(Long id);
     CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO);
     void eliminar(Long id);
}
