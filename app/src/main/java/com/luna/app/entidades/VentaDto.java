package com.luna.app.entidades;

import java.time.LocalDateTime;

public class VentaDto {

    private long id;
    private LocalDateTime fecha;
    private long producto_id;
    private int cantidad;

    public VentaDto(long id, LocalDateTime fecha, long producto_id, int cantidad) {
        this.id = id;
        this.fecha = fecha;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", fecha=" + fecha + ", producto_id=" + producto_id + ", cantidad=" + cantidad + "]";
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(long producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
