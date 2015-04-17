/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import practica.entidad.Operacionescuenta;

/**
 *
 * @author marco
 */
public class Operaciones_DB {
    
    private Conexion_DB conectDB;
    
    public Operaciones_DB() {
        this.conectDB = new Conexion_DB();
    }
    
    public void saveOperations(Operacionescuenta oc) throws HibernateException {
        
        this.conectDB.init_Operations();
        Session session= this.conectDB.getSession();
        Transaction transaction= this.conectDB.getTransaction();
        
        try {
            session.save(oc);
            transaction.commit();
        }
        catch(HibernateException ex) {
            this.conectDB.catch_Exception(ex);
            throw ex;
        }
        finally {
            session.close();
        }
        
    }
    
}
