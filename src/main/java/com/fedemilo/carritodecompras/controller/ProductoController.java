package com.fedemilo.carritodecompras.controller;

import java.util.List;

import com.fedemilo.carritodecompras.dto.SuccessResponseDTO;
import com.fedemilo.carritodecompras.model.Producto;
import com.fedemilo.carritodecompras.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
 

@RestController
public class ProductoController {

    @Autowired 
    ProductoService productoService;
    
    @PostMapping("/agregarProducto")
    public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(productoService.agregarProducto(producto)), HttpStatus.CREATED);
    }

    @PostMapping("/actualizarProductos")
    public ResponseEntity<?> actualizarProductos(@RequestBody List<Producto> productos) {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(productoService.actualizarProductos(productos)), HttpStatus.CREATED);
    }

    @GetMapping("/encontrarProducto/{productoId}")
    public ResponseEntity<?> encontrarProductoPorId(@PathVariable Long productoId) {
        return new ResponseEntity<Producto>(productoService.encontrarProductoPorId(productoId), HttpStatus.OK);
    }

    @GetMapping("/obtenerProductos")
    public ResponseEntity<?> obtenerProductos() {
        return new ResponseEntity<List<Producto>>(productoService.obtenerProductos(), HttpStatus.OK);
    }

    @PutMapping("/actualizarProducto/{productoId}")
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto dto, @PathVariable Long productoId) {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(productoService.actualizarProducto(dto, productoId)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrarProducto/{productoId}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long productoId) {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(productoService.borrarProducto(productoId)), HttpStatus.OK);
    }

    
}