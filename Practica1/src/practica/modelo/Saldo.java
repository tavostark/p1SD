/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.modelo;

import practica.db.Cuenta_DB;

/**
 *
 * @author marco
 */
public class Saldo {
    
    private Long cuenta;
    private Double balance;
    
    public Saldo() {
        
    }
    
    public Saldo(Long cuenta) {
        this.cuenta = cuenta;
    }
    
    public Double getSaldo() {
        
        Cuenta_DB cuDB= new Cuenta_DB();
        
        this.balance= cuDB.getBalance(this.cuenta);
        
        return this.balance;
        
    }

    /**
     * @return the cuenta
     */
    public Long getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Long cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}
