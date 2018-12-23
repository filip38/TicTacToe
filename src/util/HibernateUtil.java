/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Grupa1
 */
public class HibernateUtil {
      private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
        Configuration config = new Configuration();
        config.configure("/xml/hibernate.cfg.xml");     
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
        srb.applySettings(config.getProperties());
        ServiceRegistry servis = srb.build();
        sessionFactory = config.buildSessionFactory(servis);
        }  
        return sessionFactory;
    }

}
