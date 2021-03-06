package practica.entidad;
// Generated 15/04/2015 05:32:44 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cuenta generated by hbm2java
 */
@Entity
@Table(name="cuenta"
    ,catalog="mydb"
)
public class Cuenta  implements java.io.Serializable {


     private long idCuenta;
     private double balance;
     private int idClientes;

    public Cuenta() {
    }

    public Cuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    public Cuenta(long idCuenta, double balance, int idClientes) {
       this.idCuenta = idCuenta;
       this.balance = balance;
       this.idClientes = idClientes;
    }
   
     @Id 

    
    @Column(name="idCuenta", unique=true, nullable=false)
    public long getIdCuenta() {
        return this.idCuenta;
    }
    
    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }

    
    @Column(name="balance", nullable=false, precision=22, scale=0)
    public double getBalance() {
        return this.balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    
    @Column(name="idClientes", nullable=false)
    public int getIdClientes() {
        return this.idClientes;
    }
    
    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
    }




}


