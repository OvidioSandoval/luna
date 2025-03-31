package com.luna.app.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "venta")
@Validated
public class Ventas {

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto productos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private Integer cantidad;

    
    public Ventas() {

    }
    
    public Ventas(Producto productos, Long id, LocalDateTime fecha, Integer cantidad) {
        this.productos = productos;
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    
    @Override
    public String toString() {
        return "[productos=" + productos + ", id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + "]";
    }

    public Producto getProductos() {
        return productos;
    }
    public void setProductos(Producto productos) {
        this.productos = productos;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
}
