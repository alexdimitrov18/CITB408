package org.example.configuration;



import org.example.entities.Warehouse;
import org.example.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Cashier.class); //We have to add each class in the session
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Receipt.class);
            configuration.addAnnotatedClass(Warehouse.class);
            configuration.addAnnotatedClass(Goods.class);
            configuration.addAnnotatedClass(Store.class);
            configuration.addAnnotatedClass(Receipt.class);

            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
