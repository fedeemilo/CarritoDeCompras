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
}