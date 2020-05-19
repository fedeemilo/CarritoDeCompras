package com.fedemilo.carritodecompras.service.serviceImpl;

import java.util.List;

import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Carrito;
import com.fedemilo.carritodecompras.model.Usuario;
import com.fedemilo.carritodecompras.repository.CarritoRepository;
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

	@Override
	public String crearCarrito(Long usuarioDni) throws DuplicateElementException {

		Usuario usuarioExiste = usuarioRepository.findByDniUsuario(usuarioDni);

		if (usuarioExiste == null) {
			
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
	public List<Carrito> agregarProductoAlCarrito(List<Carrito> carritos) {
		// TODO Auto-generated method stub
		return null;
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