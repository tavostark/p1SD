/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.db;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import practica.entidad.Cuenta;

/**
 *
 * @author marco
 */
public class Cuenta_DB {

    private Conexion_DB conectDB;
    
    public Cuenta_DB() {
        this.conectDB = new Conexion_DB();
    }
    
    public int updateCuenta(Cuenta cu) throws HibernateException {
        
        int r;
        this.conectDB.init_Operations();
        Session session= this.conectDB.getSession();
        Transaction transaction= this.conectDB.getTransaction();
        
        try {
            
            session.update(cu);
            transaction.commit();
            r=1;
            
        }
        catch(HibernateException ex) {
            r=0;
            this.conectDB.catch_Exception(ex);
            throw ex;
        }
        finally {
            session.close();
        }
        
        return r;
    }
    
    public Double getBalance(Long idCuenta) throws HibernateException {
        
        this.conectDB.init_Operations();
        Double balance= null;
        Session session= this.conectDB.getSession();
        Transaction transaction= this.conectDB.getTransaction();
        
        try {
            
            balance=(Double)session.createQuery("select c.balance from Cuenta c where c.idCuenta= :cuenta")
                    .setParameter("cuenta", idCuenta)
                    .uniqueResult();
            
        }
        catch(HibernateException ex) {
            
            this.conectDB.catch_Exception(ex);
            throw ex;
            
        }
        finally {
            session.close();
        }
        
        return balance;
        
    }
    
    public int setBalance(Double balance, Long idCuenta) throws HibernateException {
        
        this.conectDB.init_Operations();
        Session session= this.conectDB.getSession();
        Transaction transaction= this.conectDB.getTransaction();
        int r;
        System.out.println("el balance es: "+balance+ " y la cuenta es: "+idCuenta);
        try {
            
            Query query=session.createQuery("update Cuenta c set c.balance= :balance where c.idCuenta= :cuenta")
                    .setParameter("balance", balance)
                    .setParameter("cuenta", idCuenta);
            
            r=query.executeUpdate();
            transaction.commit();
            
        }
        catch(HibernateException ex) {
            r=0;
            this.conectDB.catch_Exception(ex);
            throw ex;
        }
        finally {
            session.close();
        }
        
        return r;
        
    }
    
    public List<Cuenta> getCuentas(Integer idClientes) throws HibernateException {
        
        List<Cuenta> cuentas=null;
        
        this.conectDB.init_Operations();
        Session session= this.conectDB.getSession();
        Transaction transaction= this.conectDB.getTransaction();
        
        try {
            
            cuentas=session.createCriteria(Cuenta.class)
                    .add(Restrictions.eq("idClientes", idClientes))
                    .list();
            
        }
        catch(HibernateException ex) {
            this.conectDB.catch_Exception(ex);
            throw ex;
        }
        finally {
            session.close();
        }
        
        
        return cuentas;
        
    }
    
    public Cuenta getCuenta(Long idCuenta) throws HibernateException {
        
        Cuenta cuenta= null;
        this.conectDB.init_Operations();
        Session session= this.conectDB.getSession();
        Transaction transaction=  this.conectDB.getTransaction();
        
        try {
            
            cuenta=(Cuenta)session.createCriteria(Cuenta.class)
                    .add(Restrictions.eq("idCuenta", idCuenta))
                    .uniqueResult();
            
        }
        catch(HibernateException ex) {
            this.conectDB.catch_Exception(ex);
            throw ex;
        }
        finally {
            session.close();
        }
        
        return cuenta;
        
    }
    
}
