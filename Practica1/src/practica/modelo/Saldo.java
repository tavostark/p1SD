/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import practica.db.Cuenta_DB;
import practica.entidad.Cuenta;

/**
 *
 * @author marco
 */
public class Saldo {
    
    private Long cuenta;
    private Double balance;
    private Properties props;
    
    public Saldo() {
        
    }
    
    public Saldo(Long cuenta) {
        this.cuenta = cuenta;
        
        File file= new File("");
        
        this.props= new Properties();
        try {
            props.load(new FileInputStream(file.getAbsolutePath()+"/src/mensajes.properties"));
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getSaldo() {
        
        Cuenta_DB cuDB= new Cuenta_DB();
        String result=null;
        
        Cuenta account= cuDB.getCuenta(this.cuenta);
        
        if(account==null){
            result = this.props.getProperty("ms6");
        }
        else{
            this.balance= cuDB.getBalance(this.cuenta);
            
            result= this.balance.toString();
        }
        
        
        
        return result;
        
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
