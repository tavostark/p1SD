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
public class Deposito {

    private Long cuenta;
    private Double deposito;
    private Properties props;

    public Deposito() {

    }

    public Deposito(Long cuenta, Double deposito) {
        this.cuenta = cuenta;
        this.deposito = deposito;

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

    public String setDeposito() {

        String result = null;

        Cuenta_DB cuDB = new Cuenta_DB();
        Cuenta account = cuDB.getCuenta(cuenta);

        if (account == null) {
            result = this.props.getProperty("ms6");
        } else {

            Double saldo = cuDB.getBalance(this.cuenta);

            int r = cuDB.setBalance(saldo + deposito, cuenta);

            if (r != 0) {
                result = "Se han depositado " + deposito + " en su cuenta, su saldo actual es " + (saldo + deposito);
            } else {
                result = this.props.getProperty("ms5");
            }
        }

        return result;

    }

}
