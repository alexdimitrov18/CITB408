package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.configuration.SessionUtil;
import org.example.entities.Client;
import org.example.entities.Company;
import org.example.entities.Employee;
import org.example.entities.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class ClientDao {
    /**
     *
     * createClient                      ->  C from CRUD
     * getClientById(long id)            ->  R from Crud (by id)
     * getClients()                      ->  R from Crud
     *updateClients                      ->  U from CRUD
     *deleteClient                       -> D from CRUD
     *clientsWithNameEqualTo(String name) +  clientsWithNameNotEqualTo(String name) -> Retrieve client by name
     I could've made it within a single one instead of 2 but I decided to follow the lectures

     clientsWithNameLike(String name)  + clientsWithNameNotLike(String name) -> Same as Equal/notEqual to
     getClientsCompany(long id)  ->  Selecting Clients table and joining company by ids
     getClientsPurchase(long id) ->  Select clients and join purchases (if we want to check if its paid or not we join table Receipts instead)
     */
    public static void createClient(Client client   ) {  // C from CRUD
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }
    }

    public static Client getClientById(long id) {  // R from Crud
        Client client;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            transaction.commit();
        }
        return client;
    }

    public static List<Client> getClients() { // R from Crud
        List<Client> clients;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clients = session.createQuery("Select c From Client c", Client.class)
                    .getResultList();
            transaction.commit();
        }
        return clients;
    }

    public static void updateClients(Client client) {  // U from CRUD
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        }
    }

    public static void deleteClient(Client client) { // D from CRUD
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }
    public static List<Client> clientsWithNameEqualTo(String name) {   // Retrieve client by name
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> cr = cb.createQuery(Client.class);
            Root<Client> root = cr.from(Client.class);
            cr.select(root).where(cb.equal(root.get("name"), name));

            Query<Client> query = session.createQuery(cr);
            List<Client> clients = query.getResultList();
            return clients;
        }
    }
    // I could've made it within a single one instead of 2 but I decided to follow the lectures
    public static List<Client> clientsWithNameNotEqualTo(String name) {
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> cr = cb.createQuery(Client.class);
            Root<Client> root = cr.from(Client.class);
            cr.select(root).where(cb.notEqual(root.get("name"), name));

            Query<Client> query = session.createQuery(cr);
            List<Client> clients = query.getResultList();
            return clients;
        }
    }

    public static List<Client> clientsWithNameLike(String name) { // Same principle with the Like/not like
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> cr = cb.createQuery(Client.class);
            Root<Client> root = cr.from(Client.class);
            cr.select(root).where(cb.like(root.get("name"), "%" + name + "%"));

            Query<Client> query = session.createQuery(cr);
            List<Client> clients = query.getResultList();
            return clients;
        }
    }

    public static List<Client> clientsWithNameNotLike(String name) { // Same principle with the Like/not like
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> cr = cb.createQuery(Client.class);
            Root<Client> root = cr.from(Client.class);
            cr.select(root).where(cb.notLike(root.get("name"), "%" + name + "%"));

            Query<Client> query = session.createQuery(cr);
            List<Client> clients = query.getResultList();
            return clients;
        }
    }

    /**
     * Selecting Clients table and joining company by ids

     */
    public static Set<Company> getClientsCompany(long id) {
        Client client;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.createQuery(
                            "select c from Client c" +
                                    " join fetch c.company" +
                                    " where c.id = :id",
                            Client.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return client.getCompany();
    }
    // Select clients and join purchases (if we want to check if its paid or not we join table Receipts instead)
    public static Set<Purchase> getClientsPurchase(long id) {
        Client client;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.createQuery(
                            "select c from Client c" +
                                    " join fetch c.purchases" +
                                    " where c.id = :id",
                            Client.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return client.getPurchases();
    }


}
