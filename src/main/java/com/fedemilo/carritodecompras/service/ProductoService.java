package com.fedemilo.carritodecompras.service;

import java.util.List;

import com.fedemilo.carritodecompras.dto.ProductoDTO;
import com.fedemilo.carritodecompras.model.Producto;



public interface ProductoService {
    
    String agregarProducto(Producto producto);

    String actualizarProductos(List<Producto> productos); 

    Producto encontrarProductoPorId(Long productoId);

    List<Producto> obtenerProductos();

    String actualizarProducto(ProductoDTO dto, Long productoId);

    String borrarProducto(Long productoId);

 
}