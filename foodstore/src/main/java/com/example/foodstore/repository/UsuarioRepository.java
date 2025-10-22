package com.example.foodstore.repository;

import com.example.foodstore.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Producto, Long> {
}
