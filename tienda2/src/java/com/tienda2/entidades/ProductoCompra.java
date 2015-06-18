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
@Table(name = "producto_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoCompra.findAll", query = "SELECT p FROM ProductoCompra p"),
    @NamedQuery(name = "ProductoCompra.findByIdProducto", query = "SELECT p FROM ProductoCompra p WHERE p.productoCompraPK.idProducto = :idProducto"),
    @NamedQuery(name = "ProductoCompra.findByIdCompra", query = "SELECT p FROM ProductoCompra p WHERE p.productoCompraPK.idCompra = :idCompra"),
    @NamedQuery(name = "ProductoCompra.findByCantidad", query = "SELECT p FROM ProductoCompra p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "ProductoCompra.findByPrecioCompra", query = "SELECT p FROM ProductoCompra p WHERE p.precioCompra = :precioCompra")})
public class ProductoCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoCompraPK productoCompraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_compra")
    private BigDecimal precioCompra;
    @JoinColumn(name = "id_producto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "id_compra", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra;

    public ProductoCompra() {
    }

    public ProductoCompra(ProductoCompraPK productoCompraPK) {
        this.productoCompraPK = productoCompraPK;
    }

    public ProductoCompra(ProductoCompraPK productoCompraPK, int cantidad, BigDecimal precioCompra) {
        this.productoCompraPK = productoCompraPK;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
    }

    public ProductoCompra(int idProducto, int idCompra) {
        this.productoCompraPK = new ProductoCompraPK(idProducto, idCompra);
    }

    public ProductoCompraPK getProductoCompraPK() {
        return productoCompraPK;
    }

    public void setProductoCompraPK(ProductoCompraPK productoCompraPK) {
        this.productoCompraPK = productoCompraPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoCompraPK != null ? productoCompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoCompra)) {
            return false;
        }
        ProductoCompra other = (ProductoCompra) object;
        if ((this.productoCompraPK == null && other.productoCompraPK != null) || (this.productoCompraPK != null && !this.productoCompraPK.equals(other.productoCompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tienda2.entidades.ProductoCompra[ productoCompraPK=" + productoCompraPK + " ]";
    }
    
}
