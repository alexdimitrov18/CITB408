package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.Cashier;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CashierDao {
    public static void createCashier(Cashier cashier  ) {  // C from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(cashier);
            transaction.commit();
        }
    }

    public static Cashier getCashierById(long id) {  //
        Cashier cashier;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            cashier = session.get(Cashier.class, id);
            transaction.commit();
        }
        return cashier;
    }

    public static List<Cashier> getCashiers() { // r from crud
        List<Cashier> cashiers;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            cashiers = session.createQuery("Select c From Cashier c", Cashier.class)
                    .getResultList();
            transaction.commit();
        }
        return cashiers;
    }

    public static void updateCashier(Cashier cashier) {  // u from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(cashier);
            transaction.commit();
        }
    }
    public static void deleteCashier(Cashier cashier) { // d from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(cashier);
            transaction.commit();
        }
    }
}
