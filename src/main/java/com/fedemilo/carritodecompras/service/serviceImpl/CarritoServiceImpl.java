package com.fedemilo.carritodecompras.service.serviceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
			nuevoCarrito.setCantidadProductos(0);
			nuevoCarrito.setUsuarioDni(usuarioDni);
			nuevoCarrito.setEstadoTotal(new BigDecimal(0));
			nuevoCarrito.setEstadoPagado(false);
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
		
		Optional<Carrito> carrito = carritoRepository.findById(carritoId);
		Carrito carritoDB = carrito.get();
		Optional<Producto> producto = productoRepository.findById(productoId);
		Producto productoDB = producto.get();
		BigDecimal calculo;
		
		if (carrito.isPresent() && producto.isPresent()) {

			if (carritoDB.getProductoId().contains(productoId)) {
				productoDB.setCantidad(productoDB.getCantidad() + 1);
				productoRepository.save(productoDB);
			}
 
			calculo = productoDB.getPrecio().multiply(new BigDecimal(productoDB.getCantidad()));

			carritoDB.setCantidadProductos(carritoDB.getCantidadProductos() + productoDB.getCantidad());
			carritoDB.setEstadoTotal(carritoDB.getEstadoTotal().add(calculo));
			carritoDB.getProductoId().add(productoId);
			carritoRepository.save(carritoDB);

			return carritoDB;

		} else {

			throw new DataNotFoundException("No se encuentra el carrito o producto indicado.");

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

	@Override
	public Carrito eliminarProductoDelCarrito(Long productoId, Long carritoId) throws DataNotFoundException {
		
		Optional<Carrito> carrito = carritoRepository.findById(carritoId);
		Carrito carritoDB = carrito.get();
		Optional<Producto> producto = productoRepository.findById(productoId);
		Producto productoDB = producto.get();
		Set<Long> listaIds = carritoDB.getProductoId();
		BigDecimal estadoTotal;

		if (carrito.isPresent() && producto.isPresent()) {
			
			for (Long prodId: listaIds) {
				if (prodId == productoId) {
					System.out.println(prodId);
					listaIds.remove(prodId);
					estadoTotal = carritoDB.getEstadoTotal().subtract(productoDB.getPrecio().multiply(new BigDecimal(productoDB.getCantidad())));
					carritoDB.setEstadoTotal(estadoTotal);
					carritoDB.setProductoId(listaIds);
					carritoRepository.save(carritoDB);
					
				}
				System.out.println(prodId);
			}

			return carritoDB;

		} else {

			throw new DataNotFoundException("El carrito o producto indicado no existe");

		}
	}

	@Override
	public BigDecimal consultarEstadoTotal(Long carritoId) throws DataNotFoundException {
		
		Optional<Carrito> carrito = carritoRepository.findById(carritoId);
		Carrito carritoDB = carrito.get();

		if (carrito.isPresent()) {
			
			return carritoDB.getEstadoTotal();

		} else {

			throw new DataNotFoundException("No se pudo encontrar el carrito indicado.");

		}

	}

	@Override
	public BigDecimal calcularValorDelCarrito(Long carritoId) throws DataNotFoundException {
		

		Optional<Carrito> carrito = carritoRepository.findById(carritoId);
		Carrito carritoDB = carrito.get();

		int descuento;
		
		if (carrito.isPresent()) {

			BigDecimal valorDelCarrito = carritoDB.getEstadoTotal();

			// if cant productos en carrito == 5 { hacer descuento 20% general }
			if (carritoDB.getCantidadProductos() == 5) {

				descuento = 20; 
				valorDelCarrito = valorDelCarrito.multiply(new BigDecimal(descuento/100.0));
				
				// if cant productos > 10
			} else if (carritoDB.getCantidadProductos() > 10) {
				
				switch (carritoDB.getTipoCarrito()) {
					
					// if carrito == 'comun' { hacer descuento $200 }
					case "CARRITO_NORMAL":
						
						valorDelCarrito = valorDelCarrito.subtract(new BigDecimal(200));
						break;
					
					// if carrito == 'fecha_promo' { hacer descuento $500 }
					case "CARRITO_FECHA_RPOMO":

						valorDelCarrito = valorDelCarrito.subtract(new BigDecimal(500));
						break;
					
					// if carrito == 'vip' { bonificación producto más barato + descuento general $700 }
					case "CARRITO_VIP":

						BigDecimal prodMasBarato = devolverValorProductoMasBarato(carritoDB.getCarritoId());					
						valorDelCarrito = valorDelCarrito.subtract(prodMasBarato.add(new BigDecimal(700)));
						break;

				}


			}

			return valorDelCarrito;

		} else {

			throw new DataNotFoundException("No se pudo encontrar el carrito indicado.");

		}


	}

	@Override
	public BigDecimal devolverValorProductoMasBarato(Long carritoId) throws DataNotFoundException {
		
		Optional<Carrito> carrito = carritoRepository.findById(carritoId);
		Carrito carritoDB = carrito.get();
		
		BigDecimal min = new BigDecimal(999999999999999.0);

		if (carrito.isPresent()) {

			for (Long prodId: carritoDB.getProductoId()) {

				Optional<Producto> producto = productoRepository.findById(prodId);

				if (producto.get().getPrecio().compareTo(min) < 0) {

					min = producto.get().getPrecio();

				}

			}

			return min;

		} else {

			throw new DataNotFoundException("No se pudo encontrar el carrito indicado.");
		}

	}

	@Override
	public Carrito realizarPagoDelCarrito(Long carritoId) throws DataNotFoundException {
		
		Optional<Carrito> carrito = carritoRepository.findById(carritoId);
		Carrito carritoDB = carrito.get();

		if (carrito.isPresent()) {

			carritoDB.setEstadoPagado(true);
			carritoRepository.save(carritoDB);

			return carritoDB;

		} else {

			throw new DataNotFoundException("No se pudo encontrar el carrito indicado.");

		}
	}



	
    
}