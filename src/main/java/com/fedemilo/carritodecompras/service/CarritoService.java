package com.fedemilo.carritodecompras.service;

import java.util.List;

import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Carrito;

public interface CarritoService {

    String crearCarrito(Long usuarioDni) throws DuplicateElementException;
    
    Carrito agregarProductoAlCarrito(Long productoId, Long carritoId) throws DataNotFoundException;

    Carrito actualizarCarrito(Carrito carrito, Long carritoId);

    List<Carrito> obtenerTodosLosCarritos();

    String borrarCarritoPorId(Long carritoId);

}