package com.example.foodstore.Impl;

import com.example.foodstore.entity.Categoria;
import com.example.foodstore.entity.dto.CategoriaCreateDTO;
import com.example.foodstore.entity.dto.CategoriaDTO;
import com.example.foodstore.entity.mapper.CategoriaCreateMapper;
import com.example.foodstore.entity.mapper.CategoriaMapper;
import com.example.foodstore.exception.EntidadExistenteException;
import com.example.foodstore.exception.EntidadNoEncontradaException;
import com.example.foodstore.repository.CategoriaRepository;
import com.example.foodstore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaCreateMapper categoriaCreateMapper;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDTO crear(CategoriaCreateDTO categoriaCreateDTO) {
        if(categoriaRepository.findByNombre(categoriaCreateDTO.getNombre()).isPresent()){
            throw new EntidadExistenteException("La categoria ya existe!");
        }
        Categoria categoria = categoriaCreateMapper.toEntity(categoriaCreateDTO);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoriaGuardada);
    }

    @Override
    public List<CategoriaDTO> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(() -> new EntidadNoEncontradaException("Categoria no encontrada"));
        return categoriaMapper.toDto(categoriaEncontrada);
    }

    @Override
    public CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoriaActualizar = categoriaRepository.findById(id).orElseThrow(() -> new EntidadNoEncontradaException("Categoria no encontrada"));

        categoriaActualizar.setNombre(categoriaDTO.getNombre());

        Categoria categoriaGuardada = categoriaRepository.save(categoriaActualizar);
        return categoriaMapper.toDto(categoriaGuardada);
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoriaEliminar = categoriaRepository.findById(id).orElseThrow(() -> new EntidadNoEncontradaException("Categoria no encontrada"));
        categoriaRepository.delete(categoriaEliminar);
    }
}
