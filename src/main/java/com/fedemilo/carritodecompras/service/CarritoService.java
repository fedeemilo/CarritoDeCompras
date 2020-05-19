package com.fedemilo.carritodecompras.service;

import java.util.List;

import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Carrito;

public interface CarritoService {

    String crearCarrito(Long usuarioDni) throws DuplicateElementException;
    
    List<Carrito> agregarProductoAlCarrito(List<Carrito> carritos);

    Carrito actualizarCarrito(Carrito carrito, Long carritoId);

    List<Carrito> obtenerTodosLosCarritos();

    String borrarCarritoPorId(Long carritoId);

}