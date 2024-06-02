package org.example.configuration;



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
            configuration.addAnnotatedClass(Company.class); //We have to add each class in the session
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Skill.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Purchase.class);
            configuration.addAnnotatedClass(Payload.class);
            configuration.addAnnotatedClass(Receipt.class);

            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
