package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.Warehouse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WarehouseDao {
    public static void createWarehouse(Warehouse warehouse ) {  // C from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(warehouse);
            transaction.commit();
        }
    }

    public static Warehouse getWarehouseById(long id) {  //
        Warehouse warehouse;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            warehouse = session.get(Warehouse.class, id);
            transaction.commit();
        }
        return warehouse;
    }

    public static List<Warehouse> getWarehouses() { // r from crud
        List<Warehouse> warehouses;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            warehouses = session.createQuery("Select c From Warehouse c", Warehouse.class)
                    .getResultList();
            transaction.commit();
        }
        return warehouses;
    }

    public static void updateWarehouse(Warehouse warehouse) {  // u from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(warehouse);
            transaction.commit();
        }
    }
    public static void deleteWarehouse(Warehouse warehouse) { // d from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(warehouse);
            transaction.commit();
        }
    }
}
