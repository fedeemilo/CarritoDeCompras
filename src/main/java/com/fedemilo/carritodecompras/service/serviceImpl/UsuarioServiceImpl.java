package com.fedemilo.carritodecompras.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Usuario;
import com.fedemilo.carritodecompras.repository.UsuarioRepository;
import com.fedemilo.carritodecompras.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> obtenerUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public String agregarUsuario(Usuario usuario) throws DuplicateElementException {
		Usuario usuarioExiste = usuarioRepository.findByDniUsuario(usuario.getDniUsuario());

		if (usuarioExiste == null) {
			Usuario user = new Usuario();
			user.setDniUsuario(usuario.getDniUsuario());
			user.setNombreUsuario(usuario.getNombreUsuario());
			usuarioRepository.save(user);
			return "SUCCESS";
		} else {
			throw new DuplicateElementException("El usuario con username " + usuario.getNombreUsuario() + " ya existe.");
		}
	}
	
	@Override
	public String actualizarUsuario(Usuario usuario, Long usuarioId) throws DataNotFoundException {
		
		Optional<Usuario> usuarioDB = usuarioRepository.findById(usuarioId);
 
		if (usuarioDB.isPresent()) {

			Usuario usuarioActualizado = usuarioDB.get();
			usuarioActualizado.setDniUsuario(usuario.getDniUsuario());
			usuarioActualizado.setNombreUsuario(usuario.getNombreUsuario());
			usuarioRepository.save(usuarioActualizado);

            return "USUARIO ACTUALIZADO CON ÉXITO!";

		} else {
			throw new DataNotFoundException
			("El usuario con username " + usuario.getNombreUsuario() + " no se encuentra en la base de datos");
		}
	}
	@Override
	public String borrarUsuario(Long usuarioId) throws DataNotFoundException {
		
		Optional<Usuario> usuarioDB = usuarioRepository.findById(usuarioId);

		if (usuarioDB.isPresent()) {

			usuarioRepository.delete(usuarioDB.get());
			return "USUARIO ELIMINADO CON ÉXITO!";
		} else {

			throw new DataNotFoundException
			("El usuario con username " + usuarioDB.get().getNombreUsuario() + " no se encuentra en la base de datos");

		}
	}

    
}