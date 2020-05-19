package com.fedemilo.carritodecompras.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public String crearCarrito(Long usuarioDni) throws DuplicateElementException {

		Usuario usuarioExiste = usuarioRepository.findByDniUsuario(usuarioDni);

		if (usuarioExiste.getDniUsuario() == usuarioDni) {
			
			Carrito nuevoCarrito = new Carrito();
			nuevoCarrito.setUsuarioDni(usuarioDni);
			carritoRepository.save(nuevoCarrito);
			return "CARRITO CREADO CON ÉXITO";
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
	public Carrito agregarProductoAlCarrito(Long productoId, Long carritoId) throws DataNotFoundException{
		
		Optional<Carrito> carritoDB = carritoRepository.findById(carritoId);
		Optional<Producto> productoDB = productoRepository.findById(productoId);
		List<Long> prodIDs = carritoDB.get().getProductoId();

		if (carritoDB.isPresent() && productoDB.isPresent()) {
			prodIDs.add(productoId);
			carritoDB.get().setProductoId(prodIDs);
			return carritoDB.get();
		} else {
			throw new DataNotFoundException("No se encuentra");
		}


	}

	@Override
	public Carrito actualizarCarrito(Carrito carrito, Long carritoId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String borrarCarritoPorId(Long carritoId) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}