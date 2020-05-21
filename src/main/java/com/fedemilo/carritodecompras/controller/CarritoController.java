package com.fedemilo.carritodecompras.controller;

import java.math.BigDecimal;
import java.util.List;

import com.fedemilo.carritodecompras.dto.SuccessResponseDTO;
import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Carrito;
import com.fedemilo.carritodecompras.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        return new ResponseEntity<Long>(carritoService.crearCarrito(usuarioDni), HttpStatus.CREATED);
    }

    @GetMapping("/obtenerCarritos")
    public ResponseEntity<List<Carrito>> obtenerTodosLosCarritos() {
        return new ResponseEntity<List<Carrito>>(carritoService.obtenerTodosLosCarritos(), HttpStatus.OK);
    }

    @GetMapping("/consultarEstadoTotal/{carritoId}")
    public ResponseEntity<BigDecimal> consultarEstadoTotal(@PathVariable("carritoId") Long carritoId) throws DataNotFoundException {
        return new ResponseEntity<BigDecimal>(carritoService.consultarEstadoTotal(carritoId), HttpStatus.OK);
    }

    @GetMapping("calcularValorDelCarrito/{carritoId}")
    public ResponseEntity<BigDecimal> calcularValorDelCarrito(@PathVariable Long carritoId) throws DataNotFoundException {
        return new ResponseEntity<BigDecimal>(carritoService.calcularValorDelCarrito(carritoId), HttpStatus.OK);
    }

    @PutMapping("/agregarProducto/{carritoId}")
    public ResponseEntity<Carrito> agregarProductoAlCarrito(@RequestParam Long productoId, @PathVariable("carritoId") Long carritoId) throws DataNotFoundException {
        return new ResponseEntity<Carrito>(carritoService.agregarProductoAlCarrito(productoId, carritoId), HttpStatus.OK);
    }
    
    @PutMapping("/eliminarProdDelCarrito/{carritoId}")
    public ResponseEntity<Carrito> eliminarProductoDelCarrito(@RequestParam Long productoId, @PathVariable("carritoId") Long  carritoId) throws DataNotFoundException {
        return new ResponseEntity<Carrito>(carritoService.eliminarProductoDelCarrito(productoId, carritoId), HttpStatus.OK);
    }

    @PutMapping("/realizarPagoDelCarrito/{carritoId}")
    public ResponseEntity<Carrito> realizarPagoDelCarrito(@PathVariable("carritoId") Long  carritoId) throws DataNotFoundException {
        return new ResponseEntity<Carrito>(carritoService.realizarPagoDelCarrito(carritoId), HttpStatus.OK);
    }

    @DeleteMapping("/eliminarCarrito/{carritoId}")
    public ResponseEntity<?> borrarCarritoPorId(@PathVariable("carritoId") Long carritoId) throws DataNotFoundException {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(carritoService.borrarCarritoPorId(carritoId)), HttpStatus.OK);
    }


}