package com.fedemilo.carritodecompras.service;

import java.util.List;

import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Usuario;

public interface UsuarioService {

    List<Usuario> obtenerUsuarios();

    String agregarUsuario(Usuario usuario) throws DuplicateElementException;

    String actualizarUsuario(Usuario usuario, Long usuarioId) throws DataNotFoundException;

    String borrarUsuario(Long usuarioId) throws DataNotFoundException;
}