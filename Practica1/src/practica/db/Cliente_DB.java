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
public class Cliente_DB {
    
    private Conexion_DB conDB;
    
    public Cliente_DB() {
        this.conDB = new Conexion_DB();
    }
    
    public Integer consultar_Cuenta(Integer idClientes) throws HibernateException {
        
        this.conDB.init_Operations();
        Session session = this.conDB.getSession();
        Transaction transaction = this.conDB.getTransaction();
        Integer cuenta=null;
        
        try {
            
            cuenta=(Integer)session.createQuery("select c.numero from Clientes c where c.idClientes= :idCliente")
                    .setParameter("idCliente", idClientes)
                    .uniqueResult();
            
        }
        catch(HibernateException ex) {
            this.conDB.catch_Exception(ex);
            throw ex;
        }
        finally {
            session.close();
        }
        
       return cuenta; 
        
    } 
    
}
