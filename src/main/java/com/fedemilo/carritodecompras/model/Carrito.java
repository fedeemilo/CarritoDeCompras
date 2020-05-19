package com.fedemilo.carritodecompras.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Table(name = "CARRITO")
@Entity
public class Carrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carritoId;

  
    @ElementCollection
    @CollectionTable(name = "LISTA_IDS_PRODUCTOS")
    @OrderColumn
    private List<Long> productoId;

    @Column(name = "USUARIO_DNI")
    private Long usuarioDni;

    public Long getCarritoId() {
        return this.carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public List<Long> getProductoId() {
        return this.productoId;
    }

    public void setProductoId(List<Long> productoId) {
        this.productoId = productoId;
    }

    public Long getUsuarioDni() {
        return this.usuarioDni;
    }

    public void setUsuarioDni(Long usuarioDni) {
        this.usuarioDni = usuarioDni;
    }


}