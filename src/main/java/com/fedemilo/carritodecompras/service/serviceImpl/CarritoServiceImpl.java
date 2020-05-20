package com.fedemilo.carritodecompras.service.serviceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Carrito;
import com.fedemilo.carritodecompras.model.Producto;
import com.fedemilo.carritodecompras.model.Usuario;
import com.fedemilo.carritodecompras.repository.CarritoRepository;
import com.fedemilo.carritodecompras.repository.ProductoRepository;
import com.fedemilo.carritodecompras.repository.UsuarioRepository;
import com.fedemilo.carritodecompras.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServiceImpl implements CarritoService {

	@Autowired
	CarritoRepository carritoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Override
	public Long crearCarrito(Long usuarioDni) throws DuplicateElementException {

		// El día 15 de cada mes es una fecha de promoción
		LocalDate fechaActual = LocalDate.now();


		Usuario usuarioDB = usuarioRepository.findByDniUsuario(usuarioDni);

		if (usuarioDB != null) {
			
			Carrito nuevoCarrito = new Carrito();

			if (fechaActual.getDayOfMonth() == 15) {
				nuevoCarrito.setTipoCarrito("CARRITO_FECHA_PROMO");
			} else {
				if (usuarioDB.getEsVip() == true) {
					nuevoCarrito.setTipoCarrito("CARRITO_VIP");
				} else {
					nuevoCarrito.setTipoCarrito("CARRITO_NORMAL");
				} 
				
			}
		
			nuevoCarrito.setUsuarioDni(usuarioDni);
			nuevoCarrito.setEstadoTotal(new BigDecimal(0));
			carritoRepository.save(nuevoCarrito);
			return nuevoCarrito.getCarritoId();
		} else {
			throw new DuplicateElementException
			("El usuario con DNI " + usuarioDni + " no existe en la base de datos");
		}

	}

	@Override
	public List<Carrito> obtenerTodosLosCarritos() {
		List<Carrito> carritos = carritoRepository.findAll();

		return carritos;
	}

	@Override
	public Carrito agregarProductoAlCarrito(Long productoId, Long carritoId) throws DataNotFoundException {
		
		Optional<Carrito> carritoDB = carritoRepository.findById(carritoId);
		Optional<Producto> productoDB = productoRepository.findById(productoId);
		BigDecimal total;
		BigDecimal calculo;

		if (carritoDB.isPresent() && productoDB.isPresent()) {
			calculo = productoDB.get().getPrecio().multiply(new BigDecimal(productoDB.get().getCantidad()));
			total = carritoDB.get().getEstadoTotal().add(calculo);
			carritoDB.get().setEstadoTotal(total);
			carritoDB.get().getProductoId().add(productoId);
			carritoRepository.save(carritoDB.get());
			return carritoDB.get();
		} else {
			throw new DataNotFoundException("No se encuentra");
		}


	}
	
	@Override
	public String borrarCarritoPorId(Long carritoId) throws DataNotFoundException {
		
		Optional<Carrito> carritoDB = carritoRepository.findById(carritoId);

		if (carritoDB.isPresent()) {
			carritoRepository.delete(carritoDB.get());
			return "CARRITO ELIMINADO CON ÉXITO.";
		} else {
			throw new DataNotFoundException("El carrito con ID " + carritoId + " no se encuentra en la base de datos.");
		}
	}

	
    
}