/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.modelo;

import practica.entidad.Cuenta;

/**
 *
 * @author marco
 */
public class Transferencia {
    
    private Double monto;
    private Cuenta cuenta1;
    private Cuenta cuenta2;
    
    public Transferencia() {
        
    }
    
    public Transferencia(Double monto) {
        this.monto = monto;
    }
    
    public Transferencia(Double monto, Cuenta cuenta1, Cuenta cuenta2) {
        this.monto= monto;
        this.cuenta1= cuenta1;
        this.cuenta2= cuenta2;
    }
    
    public Integer setTransferencia() {
        
        Integer result=null;
        
        return result;
        
    }

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the cuenta1
     */
    public Cuenta getCuenta1() {
        return cuenta1;
    }

    /**
     * @param cuenta1 the cuenta1 to set
     */
    public void setCuenta1(Cuenta cuenta1) {
        this.cuenta1 = cuenta1;
    }

    /**
     * @return the cuenta2
     */
    public Cuenta getCuenta2() {
        return cuenta2;
    }

    /**
     * @param cuenta2 the cuenta2 to set
     */
    public void setCuenta2(Cuenta cuenta2) {
        this.cuenta2 = cuenta2;
    }
    
}
