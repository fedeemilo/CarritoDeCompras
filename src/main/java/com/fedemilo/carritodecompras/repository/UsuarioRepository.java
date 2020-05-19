package com.fedemilo.carritodecompras.repository;

import com.fedemilo.carritodecompras.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Usuario findByDniUsuario(Long dniUsuario);
}