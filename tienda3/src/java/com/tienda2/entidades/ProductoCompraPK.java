/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda2.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jhonf
 */
@Embeddable
public class ProductoCompraPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_compra")
    private int idCompra;

    public ProductoCompraPK() {
    }

    public ProductoCompraPK(int idProducto, int idCompra) {
        this.idProducto = idProducto;
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) idCompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoCompraPK)) {
            return false;
        }
        ProductoCompraPK other = (ProductoCompraPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idCompra != other.idCompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tienda2.entidades.ProductoCompraPK[ idProducto=" + idProducto + ", idCompra=" + idCompra + " ]";
    }
    
}
