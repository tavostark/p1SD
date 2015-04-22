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
public class Retiro {

    private String dinero;
    private Long cuenta;
    private Properties props;

    public Retiro() {

    }

    public Retiro(String dinero, Long cuenta) {
        this.dinero = dinero;
        this.cuenta = cuenta;

        File file = new File("");

        this.props = new Properties();
        try {
            props.load(new FileInputStream(file.getAbsolutePath() + "/src/mensajes.properties"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getRetiro() {

        String retiro = null;

        Cuenta_DB cuDB = new Cuenta_DB();

        Cuenta account = cuDB.getCuenta(cuenta);

        if (account == null) {
            retiro = this.props.getProperty("ms6");
        } else {

            Double balance = cuDB.getBalance(cuenta);
            Double efectivo = Double.parseDouble(dinero);

            if (efectivo > balance) {
                retiro = this.props.getProperty("ms4");
            } else if (efectivo <= balance) {

                balance = balance - efectivo;
                cuDB.setBalance(balance, cuenta);

                Operacionescuenta oc = new Operacionescuenta(Fecha.getNow(), 'r', efectivo, this.cuenta);

                Operaciones_DB opDB = new Operaciones_DB();
                opDB.saveOperations(oc);

                retiro = "Retiro exitoso, tu saldo actual es: " + balance;

            }
        }

        return retiro;

    }

    /**
     * @return the dinero
     */
    public String getDinero() {
        return dinero;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(String dinero) {
        this.dinero = dinero;
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

}
