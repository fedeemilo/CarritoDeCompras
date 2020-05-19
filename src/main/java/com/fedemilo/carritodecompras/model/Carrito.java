package com.fedemilo.carritodecompras.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CARRITO")
@Entity
public class Carrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carritoId;

    @ElementCollection
    private List<String> productoId;

    @Column(name = "USUARIO_DNI")
    private Long usuarioDni;

    public Long getCarritoId() {
        return this.carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public List<String> getProductoId() {
        return this.productoId;
    }

    public void setProductoId(List<String> productoId) {
        this.productoId = productoId;
    }

    public Long getUsuarioDni() {
        return this.usuarioDni;
    }

    public void setUsuarioDni(Long usuarioDni) {
        this.usuarioDni = usuarioDni;
    }


}