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


public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        Session session = SessionUtil.getSessionFactory().openSession();



        Dummy_data dummy ;
        dummy   = new Dummy_data ();
        dummy.dummy();


    }
}