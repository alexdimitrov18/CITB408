package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class PurchaseDao {
    /**
     * createPurchase                    ->  C from CRUD
     * getPurchaseById(long id)            ->  R from Crud (by id)
     * getPurchases()                      ->  R from Crud
     * updatePurchase                     ->  U from CRUD
     * deletePurchase                   -> D from CRUD
     *
     *
     * updatePurchaseSafe - Originally planned to have additional validations when creating a purchase, even to throw error but didn't have time to fully test it
     * getPurchaseCompany   Select purchase table and join company
     * getPurchasePayload   Similar principle
     * getPurchaseVehicle   Similar principle
     * getPurchaseEmployee  Similar principle
     * getPurchaseClient    Similar principle
     * getPurchaseByArrivalPoint -> Point 7c of the requirements
     */
    public static void createPurchase(Purchase purchase) {  // C FROM CRUD
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(purchase);
            transaction.commit();
        }
    }

    public static Purchase getPurchaseById(long id) {  // R FROM CRUD (ID)
        Purchase purchase;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            purchase = session.get(Purchase.class, id);
            transaction.commit();
        }
        return purchase;
    }

    public static List<Purchase> getPurchases() { // R FROM CRUD
        List<Purchase> purchases;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            purchases = session.createQuery("Select c From Purchase c", Purchase.class)
                    .getResultList();
            transaction.commit();
        }
        return purchases;
    }

    public static void updatePurchase(Purchase purchase) {  // U FROM CRUD
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.update(purchase);
            transaction.commit();
        }
    }
    // Part of the validation requirements, didn't have time to fully test and implement it
    public static void updatePurchaseSafe(Purchase purchase) throws IllegalAccessException {  // ok
        Payload payload = purchase.getPayload();
        Employee employee = purchase.getEmployee();
        Vehicle vehicle = purchase.getVehicle();

        if (vehicle.getType().equals("Bus") & vehicle.getUnit().equals(UnitType.People) & employee.getSkills().contains(payload.getSkills())) {
            try (Session session = SessionUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(purchase);
                transaction.commit();
            }
        } else if (vehicle.getType().equals("Gas") & vehicle.getUnit().equals(UnitType.Litre) & employee.getSkills().contains(payload.getSkills())) {
            try (Session session = SessionUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(purchase);
                transaction.commit();
            }
        } else if (vehicle.getType().equals("Freight") & vehicle.getUnit().equals(UnitType.Kilograms) & employee.getSkills().contains(payload.getSkills())) {


            try (Session session = SessionUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(purchase);
                transaction.commit();
            }
        }

        else if (vehicle.getType().equals("Samolet") & vehicle.getUnit().equals(UnitType.People) & employee.getSkills().contains(payload.getSkills())) {


            try (Session session = SessionUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(purchase);
                transaction.commit();
            }
        } else {
            throw new IllegalAccessException(" Vehicle type and units dont match the employee's skill or the payload type");
        }
    }


    public static void deletePurchase(Purchase purchase) { // D FROM CRUD
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(purchase);
            transaction.commit();
        }
    }

    public static Company getPurchaseCompany(long id) {  // Get the company which will fulfill the purchase
        Purchase purchase;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            purchase = session.createQuery(
                            "select c from Purchase c" +
                                    " join fetch c.company" +
                                    " where c.id = :id",
                            Purchase.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return purchase.getCompany();
    }

    public static Employee getPurchaseEmployee(long id) {  // Get the employee which made the delivery
        Purchase purchase;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            purchase = session.createQuery(
                            "select c from Purchase c" +
                                    " join fetch c.employee" +
                                    " where c.id = :id",
                            Purchase.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return purchase.getEmployee();
    }

    public static Vehicle getPurchaseVehicle(long id) {  // Same thing, with the vehicle
        Purchase purchase;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            purchase = session.createQuery(
                            "select c from Purchase c" +
                                    " join fetch c.vehicle" +
                                    " where c.id = :id",
                            Purchase.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return purchase.getVehicle();
    }

    public static Payload getPurchasePayload(long id) {  // Same, with the payload
        Purchase purchase;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            purchase = session.createQuery(
                            "select c from Purchase c" +
                                    " join fetch c.payload" +
                                    " where c.id = :id",
                            Purchase.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return purchase.getPayload();
    }

    public static Set<Client> getPurchaseClient(long id) {  // Get the client who ordered it
        Purchase purchase;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            purchase = session.createQuery(
                            "select c from Purchase c" +
                                    " join fetch c.clients" +
                                    " where c.id = :id",
                            Purchase.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return purchase.getClients();
    }

    public static List<Purchase> getPurchaseByArrivalPoint(String arrival_point) {// Point 7c of the requirements
        List<Purchase> purchases;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryBuilder = new StringBuilder(" ");

            purchases = session.createQuery(" select p from Purchase p " +
                            " where p.arrival_point = :arrival_point" , Purchase.class)
                    .setParameter("arrival_point", arrival_point)
                    .getResultList();
            transaction.commit();
        }
        return purchases ;
    }

}
