package com.example.foodstore.repository;

import com.example.foodstore.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuario_id(Long id);
}
