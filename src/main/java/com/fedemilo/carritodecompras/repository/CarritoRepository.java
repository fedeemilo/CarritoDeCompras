package com.fedemilo.carritodecompras.repository;

import com.fedemilo.carritodecompras.model.Carrito;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

}
