package org.example.dao;

import jakarta.persistence.OrderBy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.configuration.SessionUtil;
import org.example.dto.*;
import org.example.entities.Company;
import org.example.entities.Employee;
import org.example.entities.Payload;
import org.example.entities.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 *  createEmployee                     ->  C from CRUD
 *  getEmployeeById(long id)            ->  R from Crud (by id)
 *  getEmployee()                      ->  R from Crud
 *  updateEmployee                      ->  U from CRUD
 *  deleteEmployee                      -> D from CRUD
 *  employeesWithNameEqualTo(String name) +  employeesWithNameNotEqualTo(String name) -> Retrieve client by name
 *  I could've made it within a single one instead of 2 but I decided to follow the lectures
 *
 *  employeesWithNameLike(String name)  + employeesWithNameNotLike(String name) -> Same as Equal/notEqual to
 *  getEmployeesSkills -> list the skills of an employee. Select employee table and join skills table
 *  getEmployeesBySkill -> list the employees with a certain skill   Select skill table and join employee table
 *  getEmployeesCompany  -> gets the company of an employee. Select employee table and join company table
 *  getEmployeePurchasesTotal(long id)  -> Select Employee table and join company and purchases tables  // point 9 of the requirements
 *  getEmployeeProfitsTotal -> same as the purchases, but added receipt table join. If an receipt exists - the delivery is paid and can be counted as profit
 *
 */
public class EmployeeDAO {

    public static void createEmployee(Employee employee ) {  // C from CRUD
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static Employee getEmployeeById(long id) {  //
        Employee employee;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static List<Employee> getEmoplyees() { // R from CRUD
        List<Employee> employees;
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery("Select c From Employee c", Employee.class)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }

    public static void updateEmployee(Employee employee) {  // U from CRUD
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    public static void deleteEmployee(Employee employee) { // D from CRUD
        try(Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    public static List<Employee> employeesWithNameEqualTo(String name) {   //check employee by name
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.equal(root.get("name"), name));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> employeesWithNameNotEqualTo(String name) {   //
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.notEqual(root.get("name"), name));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> employeesWithNameLike(String name) {   // Employees with similar name
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.like(root.get("name"), "%" + name + "%"));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> employeesWithNameNotLike(String name) {   //
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.notLike(root.get("name"), "%" + name + "%"));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static Company getEmployeesCompany(long id) {  // Get an  employee's company
        Employee employee;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.createQuery(
                            "select c from Employee c" +
                                    " join fetch c.company" +
                                    " where c.id = :id",
                            Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return employee.getCompany();
    }



    public static Set<Skill> getEmployeesSkills(long id) {  // list the skills of an employee
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

    // Get all employees by a certain skill, for example bus drivers
    public static List<Employee> getEmployeesBySkill(String skill) {
        List<Employee> employee;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StringBuilder queryBuilder = new StringBuilder(" ");

            employee = session.createQuery(" select e from Employee e " +
                            " join e.skills pQ " +
                            " where pQ.type = :type" , Employee.class)
                    .setParameter("type", skill)
                    .getResultList();
            transaction.commit();
        }
        return employee;
    }

    public static List<EmployeePurchaseTotalDTO> getEmployeePurchasesTotal(long id) {  // point 9 of the requirements
        List<EmployeePurchaseTotalDTO> employeePurchaseTotalDTOS;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeePurchaseTotalDTOS =  session.createQuery(
                            "select new org.example.dto.EmployeePurchaseTotalDTO( e.name, e.family_name, c.name, Count(p.id)) from Employee e" +
                                    " join  e.company c" +
                                    " join  c.purchases p " +
                                    " where c.id = :id",
                            EmployeePurchaseTotalDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return employeePurchaseTotalDTOS;
    }
    public static List<EmployeeProfitDTO> getEmployeeProfitTotal(long id) {  // point 9 of the requirements
        List<EmployeeProfitDTO> employeeProfitDTOS;
        try (Session session = SessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeProfitDTOS =  session.createQuery(
                            "select new org.example.dto.EmployeeProfitDTO ( e.name, e.family_name, c.name, Sum(p.price) ) from Employee e" +
                                    " join  e.company c" +
                                    " join  c.purchases p " +
                                    " join  p.receipts r " +
                                    " where c.id = :id and r.id is not null",
                            EmployeeProfitDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return employeeProfitDTOS;
    }

}
