package com.fedemilo.carritodecompras.controller;

import java.util.List;

import com.fedemilo.carritodecompras.dto.SuccessResponseDTO;
import com.fedemilo.carritodecompras.exceptions.DataNotFoundException;
import com.fedemilo.carritodecompras.exceptions.DuplicateElementException;
import com.fedemilo.carritodecompras.model.Usuario;
import com.fedemilo.carritodecompras.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/obtenerUsuarios")
    public ResponseEntity<?> obtenerUsuarios() {
        return new ResponseEntity<List<Usuario>>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }
    
    @PostMapping("/agregarUsuario")
    public ResponseEntity<?> agregarUsuario(@RequestBody Usuario user) throws DuplicateElementException {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(usuarioService.agregarUsuario(user)), HttpStatus.CREATED);
    }

    @PutMapping("/actualizarUsuario/{usuarioId}")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario user, @PathVariable Long usuarioId) throws DataNotFoundException {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(usuarioService.actualizarUsuario(user, usuarioId)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/eliminarUsuario/{usuarioId}")
    public ResponseEntity<?> borrarUsuario(@PathVariable Long usuarioId) throws DataNotFoundException {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(usuarioService.borrarUsuario(usuarioId)), HttpStatus.OK);
    }
}