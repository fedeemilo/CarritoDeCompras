package com.fedemilo.carritodecompras.repository;

import com.fedemilo.carritodecompras.model.ProductoOrden;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoOrdenRepository extends JpaRepository<ProductoOrden, Long> {
    
}