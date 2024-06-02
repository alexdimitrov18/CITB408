package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.Goods;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GoodsDao {
    public static void createGoods(Goods goods ) {  // C from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(goods);
            transaction.commit();
        }
    }

    public static Goods getGoodsById(long id) {  //
        Goods goods;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goods = session.get(Goods.class, id);
            transaction.commit();
        }
        return goods;
    }

    public static List<Goods> getGoodss() { // r from crud
        List<Goods> goodss;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goodss = session.createQuery("Select c From Goods c", Goods.class)
                    .getResultList();
            transaction.commit();
        }
        return goodss;
    }

    public static void updateGoods(Goods goods) {  // u from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(goods);
            transaction.commit();
        }
    }
    public static void deleteGoods(Goods goods) { // d from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(goods);
            transaction.commit();
        }
    }
}
