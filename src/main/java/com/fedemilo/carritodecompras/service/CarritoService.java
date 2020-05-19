package com.fedemilo.carritodecompras.service;

import java.util.List;

import com.fedemilo.carritodecompras.model.Carrito;

public interface CarritoService {
    
    List<Carrito> agregarItem(List<Carrito> carritos);

    Carrito actualizarCarrito(Carrito carrito, Long carritoId);

    List<Carrito> encontrarTodosLosCarritos();

    String borrarCarritoPorId(Long carritoId);

}