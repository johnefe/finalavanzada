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
public class VentaProductoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_venta")
    private int idVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;

    public VentaProductoPK() {
    }

    public VentaProductoPK(int idVenta, int idProducto) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVenta;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaProductoPK)) {
            return false;
        }
        VentaProductoPK other = (VentaProductoPK) object;
        if (this.idVenta != other.idVenta) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tienda2.entidades.VentaProductoPK[ idVenta=" + idVenta + ", idProducto=" + idProducto + " ]";
    }
    
}
