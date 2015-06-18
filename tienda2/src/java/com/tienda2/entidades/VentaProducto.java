/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda2.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonf
 */
@Entity
@Table(name = "venta_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaProducto.findAll", query = "SELECT v FROM VentaProducto v"),
    @NamedQuery(name = "VentaProducto.findByIdVenta", query = "SELECT v FROM VentaProducto v WHERE v.ventaProductoPK.idVenta = :idVenta"),
    @NamedQuery(name = "VentaProducto.findByIdProducto", query = "SELECT v FROM VentaProducto v WHERE v.ventaProductoPK.idProducto = :idProducto"),
    @NamedQuery(name = "VentaProducto.findByCantidad", query = "SELECT v FROM VentaProducto v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentaProducto.findByValor", query = "SELECT v FROM VentaProducto v WHERE v.valor = :valor")})
public class VentaProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaProductoPK ventaProductoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "id_venta", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ventas ventas;
    @JoinColumn(name = "id_producto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public VentaProducto() {
    }

    public VentaProducto(VentaProductoPK ventaProductoPK) {
        this.ventaProductoPK = ventaProductoPK;
    }

    public VentaProducto(VentaProductoPK ventaProductoPK, int cantidad) {
        this.ventaProductoPK = ventaProductoPK;
        this.cantidad = cantidad;
    }

    public VentaProducto(int idVenta, int idProducto) {
        this.ventaProductoPK = new VentaProductoPK(idVenta, idProducto);
    }

    public VentaProductoPK getVentaProductoPK() {
        return ventaProductoPK;
    }

    public void setVentaProductoPK(VentaProductoPK ventaProductoPK) {
        this.ventaProductoPK = ventaProductoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaProductoPK != null ? ventaProductoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaProducto)) {
            return false;
        }
        VentaProducto other = (VentaProducto) object;
        if ((this.ventaProductoPK == null && other.ventaProductoPK != null) || (this.ventaProductoPK != null && !this.ventaProductoPK.equals(other.ventaProductoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tienda2.entidades.VentaProducto[ ventaProductoPK=" + ventaProductoPK + " ]";
    }
    
}
