package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.Terminal;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TerminalDao {
    public static void createTerminal(Terminal terminal ) {  // C from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(terminal);
            transaction.commit();
        }
    }

    public static Terminal getTerminalById(long id) {  //
        Terminal Terminal;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Terminal = session.get(Terminal.class, id);
            transaction.commit();
        }
        return Terminal;
    }

    public static List<Terminal> getTerminals() { // r from crud
        List<Terminal> terminals;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            terminals = session.createQuery("Select c From Terminal c", Terminal.class)
                    .getResultList();
            transaction.commit();
        }
        return terminals;
    }

    public static void updateTerminal(Terminal terminal) {  // u from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(terminal);
            transaction.commit();
        }
    }
    public static void deleteTerminal(Terminal terminal) { // d from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(terminal);
            transaction.commit();
        }
    }
}
