package com.fedemilo.carritodecompras.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "PRODUCTOS")
@Entity
public class Producto {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productoId;


    @Column(name = "NOMBRE_PRODUCTO")
    private String nombre;


    @Column(name = "PRECIO_PRODUCTO")
    private BigDecimal precio;


    @Column(name = "CANTIDAD_PRODUCTO")
    private Integer cantidad;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductoOrden> ordenes;


    public Long getId() {
        return this.productoId;
    }

    public void setId(Long id) {
        this.productoId = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}