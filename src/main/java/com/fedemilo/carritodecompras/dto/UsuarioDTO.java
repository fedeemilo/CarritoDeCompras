package com.fedemilo.carritodecompras.dto;


public class UsuarioDTO {
    
    private String nombreUsuario;

    private Long dniUsuario;

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getDniUsuario() {
        return this.dniUsuario;
    }

    public void setDniUsuario(Long dniUsuario) {
        this.dniUsuario = dniUsuario;
    }


}