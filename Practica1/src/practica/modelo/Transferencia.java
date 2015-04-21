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
import practica.db.Operaciones_DB;
import practica.entidad.Cuenta;
import practica.entidad.Operacionescuenta;
import practica.tools.Fecha;

/**
 *
 * @author marco
 */
public class Transferencia {
    
    private Double monto;
    private Cuenta cuenta1;
    private Cuenta cuenta2;
    private Properties props;
    
    public Transferencia() {
        
    }
    
    public Transferencia(Double monto) {
        this.monto = monto;
    }
    
    public Transferencia(Double monto, Cuenta cuenta1, Cuenta cuenta2) {
        this.monto= monto;
        this.cuenta1= cuenta1;
        this.cuenta2= cuenta2;
        
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
    
    public String setTransferencia() {
        
        String result=null;
        Double balance1=null, balance2=null, total=null;
        int r,z;
        
        Cuenta_DB cuDB= new Cuenta_DB();
        balance1=cuDB.getBalance(this.cuenta1.getIdCuenta());
        
        if((balance1==null) || (this.monto > balance1)) {
            result=this.props.getProperty("ms1");
        }
        else {
            balance2=cuDB.getBalance(this.cuenta2.getIdCuenta());
            r=cuDB.setBalance((balance1-this.monto), this.cuenta1.getIdCuenta());
            
            if(r!=0){

                z=cuDB.setBalance((balance2+this.monto), this.cuenta2.getIdCuenta());
                if(z!=0){
                    Operacionescuenta oc1 = new Operacionescuenta(Fecha.getNow(), 't', this.monto, this.cuenta1.getIdCuenta());
                    Operacionescuenta oc2 = new Operacionescuenta(Fecha.getNow(), 't', this.monto, this.cuenta2.getIdCuenta());
                    
                    Operaciones_DB opDB = new Operaciones_DB();
                    opDB.saveOperations(oc1);
                    opDB.saveOperations(oc2);
                    
                    result="Se han transferido con exito "+this.monto+" pesos de la cuenta: "+this.cuenta1.getIdCuenta()+" a la cuenta: "+this.cuenta2.getIdCuenta();
                    
                }
                else {
                    result=this.props.getProperty("ms5");
                }
            }
            else {
                result=this.props.getProperty("ms5");
            }
            
            
        }
        
        
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
