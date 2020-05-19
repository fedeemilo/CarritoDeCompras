package com.fedemilo.carritodecompras.repository;


import com.fedemilo.carritodecompras.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT u FROM Usuario u WHERE u.dniUsuario=?1 ")
    Usuario findByDniUsuario(Long dniUsuario);
}