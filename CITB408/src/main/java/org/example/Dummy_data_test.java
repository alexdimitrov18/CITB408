package org.example;

import jakarta.persistence.Query;
import org.example.configuration.SessionUtil;
import org.example.dao.*;
import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
public class Dummy_data_test {
        class EmployeeSkillQuery {

            public static void main(String[] args) {
                System.out.printf("Hello and welcome!");
                Session session = SessionUtil.getSessionFactory().openSession();


                Dummy_data dummy ;
                dummy   = new Dummy_data ();
                dummy.dummy();

                printEmployeeSkillResults();

            }

            private static void printEmployeeSkillResults() {
                try (Session session = SessionUtil.getSessionFactory().openSession()) {
                    Transaction transaction = session.beginTransaction();

                    String hqlQuery = "SELECT e.name, e.family_name, e.salary, s.type " +
                            "FROM EmployeeSkillsDto es " +
                            "LEFT JOIN es.skill s " +
                            "LEFT JOIN es.employee e " +
                            "WHERE s.type = 'Bus' " +
                            "ORDER BY e.salary DESC";

                    Query query = session.createQuery(hqlQuery);

                    // Execute the query and get the results
                    List<Object[]> results = query.getResultList();

                    // Print the results
                    for (Object[] result : results) {
                        String name = (String) result[0];
                        String familyName = (String) result[1];
                        double salary = (double) result[2];
                        String skillType = (String) result[3];

                        System.out.printf("Name: %s, Family Name: %s, Salary: %.2f, Skill Type: %s%n",
                                name, familyName, salary, skillType);
                    }

                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

*/

