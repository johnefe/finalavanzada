/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;




/**
 *
 * @author jhonf
 */
@ManagedBean
@SessionScoped
public class menuventas {
    
    public menuventas(){
    }
    
    public String menu(){
    
    return "/welcomePrimefaces";
    }
    
}
