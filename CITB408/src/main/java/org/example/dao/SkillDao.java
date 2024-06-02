package org.example.dao;

import org.example.configuration.SessionUtil;
import org.example.entities.Client;
import org.example.entities.Company;
import org.example.entities.Employee;
import org.example.entities.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class SkillDao {
    /**
     * createSkill                   ->  C from CRUD
     * getSkillById(long id)            ->  R from Crud (by id)
     * getSkills()                      ->  R from Crud
     * updateSkills                   ->  U from CRUD
     * deleteSkills                  -> D from CRUD
     *
     *getEmployeesSkill -> Employee XXX has skills a, b, c
     */
    public static void createSkill(Skill skill   ) {  // c from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(skill);
            transaction.commit();
        }
    }

    public static Skill getSkillById(long id) {  //
        Skill skill;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            skill = session.get(Skill.class, id);
            transaction.commit();
        }
        return skill;
    }

    public static List<Skill> getSkills() { // r from crud
        List<Skill> skills;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            skills = session.createQuery("Select c From Skill c", Skill.class)
                    .getResultList();
            transaction.commit();
        }
        return skills;
    }

    public static void updateSkills(Skill skill) {  // u from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(skill);
            transaction.commit();
        }
    }

    public static void deleteSkill(Skill skill) { // d from crud
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(skill);
            transaction.commit();
        }
    }

    public static Set<Skill> getEmployeesSkill(long id) {  // Employee XXX has skills a, b, c
        Employee employee;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.createQuery(
                            "select c from Employee c" +
                                    " join fetch c.skills" +
                                    " where c.id = :id",
                            Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return employee.getSkills();
    }
}
