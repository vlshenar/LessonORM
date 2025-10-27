package com.github.vlshenar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeManager {
    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(com.github.vlshenar.Employee.class)
                .buildSessionFactory();
    }

    public List<Employee> getAllEmployees() {
        try(Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee",Employee.class);
            return query.list();
        }
    }

    public void addEmployee(Employee employee) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        }
    }
}
