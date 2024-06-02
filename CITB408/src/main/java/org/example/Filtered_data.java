package org.example;

import jakarta.persistence.Query;
import org.example.configuration.SessionUtil;
import org.example.dao.*;
import org.example.entities.Company;
import org.example.entities.Employee;
import org.example.entities.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Filtered_data {
    private EmployeeDAO employeeDAO;
    private VehicleDao vehicleDao;
    private ClientDao clientDao;
    private PayloadDao payloadDao;
    private CompanyDao companyDao;
    private PurchaseDao purchaseDao;
    public Filtered_data() {
        employeeDAO = new EmployeeDAO();
        vehicleDao = new VehicleDao();
        clientDao = new ClientDao();
        payloadDao = new PayloadDao();
        companyDao = new CompanyDao();
        purchaseDao = new PurchaseDao();
    }

    //slujitel po skill i zaplata

   /* public static Employee getEmployeeSkillandSalary(long id) {  // ok
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
    } */
}
