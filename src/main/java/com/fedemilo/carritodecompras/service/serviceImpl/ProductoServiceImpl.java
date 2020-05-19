package com.fedemilo.carritodecompras.service.serviceImpl;


import java.util.List;
import java.util.Optional;

import com.fedemilo.carritodecompras.dto.ProductoDTO;
import com.fedemilo.carritodecompras.model.Producto;
import com.fedemilo.carritodecompras.repository.ProductoRepository;
import com.fedemilo.carritodecompras.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

	@Override
	public String agregarProducto(Producto producto) {

                productoRepository.save(producto);

                return "GUARDADO EXITOSO!";

	}

	@Override
	public String actualizarProductos(List<Producto> productos) {
		for (Producto producto: productos) {
           
                        productoRepository.save(producto);
                }

                return "PRODUCTOS ACUALIZADOS";
	}

	@Override
	public Producto encontrarProductoPorId(Long productoId) {

                Optional<Producto> producto = productoRepository.findById(productoId);
                
                return producto.get();
	}

	@Override
	public List<Producto> obtenerProductos() {
                List<Producto> productos = productoRepository.findAll();

                return productos;
	}

	@Override
	public String actualizarProducto(ProductoDTO dto, Long productoId) {
        
                Optional<Producto> producto = productoRepository.findById(productoId);

                if (producto.isPresent()) {

                Producto productoActualizado = producto.get();
                productoActualizado.setNombre(dto.getNombre());
                productoActualizado.setPrecio(dto.getPrecio());
                productoActualizado.setCantidad(dto.getCantidad());
                productoRepository.save(productoActualizado);

                return "PRODUCTO ACTUALIZADO CON ÉXITO!";
                }

                return "EL PRODUCTO NO SE ENCUENTRA";
	}

	@Override
	public String borrarProducto(Long productoId) {
        
                Optional<Producto> producto = productoRepository.findById(productoId);

                if (producto.isPresent()) {
                productoRepository.delete(producto.get());
                return "PRODUCTO ELIMINADO CON ÉXITO.";
                }

                return "EL PRODUCTO NO SE ENCUENTRA";
	}
    
}