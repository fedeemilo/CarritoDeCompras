package com.fedemilo.carritodecompras.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "USUARIO")
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usuarioId;

    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    @Column(name = "DNI_USUARIO")
    private Long dniUsuario;

    @Column(name = "TIPO_USUARIO")
    private Boolean esVip;

    public Long getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

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


    public Boolean getEsVip() {
        return this.esVip;
    }

    public void setEsVip(Boolean esVip) {
        this.esVip = esVip;
    }

   
} 