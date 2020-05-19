package com.fedemilo.carritodecompras.service;

import java.util.List;

import com.fedemilo.carritodecompras.model.ProductoOrden;

public interface ProductoOrdenService {
    
    ProductoOrden realizarOrden(ProductoOrden dto);

    ProductoOrden encontrarOrdenPorId(Long ordenId);

    List<ProductoOrden> encontrarOrdenPorUsuario(Long usuarioId);

    String cancelarOrden(Long ordenId);

}