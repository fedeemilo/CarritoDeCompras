package com.fedemilo.carritodecompras.controller;

import java.util.List;

import com.fedemilo.carritodecompras.dto.SuccessResponseDTO;
import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Carrito;
import com.fedemilo.carritodecompras.model.Producto;
import com.fedemilo.carritodecompras.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarritoController {
    
    @Autowired
    private CarritoService carritoService;

    @PostMapping("/crearCarrito/{usuarioDni}")
    public ResponseEntity<?> crearCarrito(@PathVariable("usuarioDni") Long usuarioDni) throws DuplicateElementException {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(carritoService.crearCarrito(usuarioDni)), HttpStatus.CREATED);
    }

    @GetMapping("/obtenerCarritos")
    public ResponseEntity<List<Carrito>> obtenerTodosLosCarritos() {
        return new ResponseEntity<List<Carrito>>(carritoService.obtenerTodosLosCarritos(), HttpStatus.OK);
    }

    @PutMapping("/agregarProducto/{productoId}/{carritoId}")
    public ResponseEntity<Carrito> agregarProductoAlCarrito(@PathVariable("productoId") Long productoId, @PathVariable("carritoId") Long carritoId) throws DataNotFoundException {
        return new ResponseEntity<Carrito>(carritoService.agregarProductoAlCarrito(productoId, carritoId), HttpStatus.OK);
    }

}