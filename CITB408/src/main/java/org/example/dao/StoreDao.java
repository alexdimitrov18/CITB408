package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.Store;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StoreDao {
    public static void createStore(Store store ) {  // C from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(store);
            transaction.commit();
        }
    }

    public static Store getStoreById(long id) {  //
        Store store;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            store = session.get(Store.class, id);
            transaction.commit();
        }
        return store;
    }

    public static List<Store> getStores() { // r from crud
        List<Store> stores;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            stores = session.createQuery("Select c From Store c", Store.class)
                    .getResultList();
            transaction.commit();
        }
        return stores;
    }

    public static void updateStore(Store store) {  // u from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(store);
            transaction.commit();
        }
    }
    public static void deleteStore(Store store) { // d from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(store);
            transaction.commit();
        }
    }
}
