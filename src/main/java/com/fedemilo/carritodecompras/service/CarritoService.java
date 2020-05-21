package com.fedemilo.carritodecompras.service;

import java.math.BigDecimal;
import java.util.List;

import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Carrito;

public interface CarritoService {

    Long crearCarrito(Long usuarioDni) throws DuplicateElementException;
    
    Carrito agregarProductoAlCarrito(Long productoId, Long carritoId) throws DataNotFoundException;

    List<Carrito> obtenerTodosLosCarritos();

    Carrito eliminarProductoDelCarrito(Long productoId, Long carritoId) throws DataNotFoundException;

    String borrarCarritoPorId(Long carritoId) throws DataNotFoundException;

    BigDecimal consultarEstadoTotal(Long carritoId) throws DataNotFoundException;

    BigDecimal calcularValorDelCarrito(Long carritoId) throws DataNotFoundException;

    BigDecimal devolverValorProductoMasBarato(Long carritoId) throws DataNotFoundException;

    Carrito realizarPagoDelCarrito(Long carritoId) throws DataNotFoundException;
    
}