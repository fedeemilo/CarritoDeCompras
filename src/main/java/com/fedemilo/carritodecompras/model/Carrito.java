package com.fedemilo.carritodecompras.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Long> productoId = new HashSet<>();

    @Column(name = "USUARIO_DNI")
    private Long usuarioDni;

    private BigDecimal estadoTotal;

    private String tipoCarrito;

    private Integer cantidadProductos;

    private Boolean estadoPagado;

    public Long getCarritoId() {
        return this.carritoId;
    }

    public Boolean getEstadoPagado() {
		return estadoPagado;
	}

	public void setEstadoPagado(Boolean estadoPagado) {
		this.estadoPagado = estadoPagado;
	}

	public Integer getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public String getTipoCarrito() {
		return tipoCarrito;
	}

	public void setTipoCarrito(String tipoCarrito) {
		this.tipoCarrito = tipoCarrito;
	}

	public BigDecimal getEstadoTotal() {
		return estadoTotal;
	}

	public void setEstadoTotal(BigDecimal estadoTotal) {
		this.estadoTotal = estadoTotal;
	}

	public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Set<Long> getProductoId() {
        return this.productoId;
    }

    public void setProductoId(Set<Long> productoId) {
        this.productoId = productoId;
    }

    public Long getUsuarioDni() {
        return this.usuarioDni;
    }

    public void setUsuarioDni(Long usuarioDni) {
        this.usuarioDni = usuarioDni;
    }


}