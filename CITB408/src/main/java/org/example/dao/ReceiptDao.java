package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReceiptDao {
    /**
     * createReceipt                   ->  C from CRUD
     * getReceiptById(long id)            ->  R from Crud (by id)
     * getReceipt()                      ->  R from Crud
     * updateReceipt                    ->  U from CRUD
     * deleteReceipt                   -> D from CRUD
     *
     *getReceiptsClient -> Check the receipt to see which client made the purchase for it
     *getReceiptsPurchase -> receipt for the payment of the delivery
     */
    public static void createReceipt(Receipt receipt ) {  // C from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(receipt);
            transaction.commit();
        }
    }

    public static Receipt getReceiptById(long id) {  //
        Receipt receipt;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            receipt = session.get(Receipt.class, id);
            transaction.commit();
        }
        return receipt;
    }

    public static List<Receipt> getReceipts() { // r from crud
        List<Receipt> receipts;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            receipts = session.createQuery("Select c From Receipt c", Receipt.class)
                    .getResultList();
            transaction.commit();
        }
        return receipts;
    }

    public static void updateReceipts(Receipt receipt) {  // u from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(receipt);
            transaction.commit();
        }
    }
    public static void deleteReceipt(Receipt receipt) { // d from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(receipt);
            transaction.commit();
        }
    }


}
