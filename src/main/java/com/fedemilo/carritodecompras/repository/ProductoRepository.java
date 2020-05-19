package com.fedemilo.carritodecompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fedemilo.carritodecompras.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}