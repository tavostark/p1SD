/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alumno
 */
public class Conexion_DB {
    
    private Session session;
    private Transaction transaction;
    
    public Conexion_DB() {
        
    }
    
    public void init_Operations() {
        
        this.setSession(HibernateUtil.getSessionFactory().openSession());
        this.setTransaction(this.getSession().beginTransaction());
        
    }
    
    public void catch_Exception(HibernateException ex) {
        System.out.println("Error en la capa de acceso de datos");
        throw ex;
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
}
