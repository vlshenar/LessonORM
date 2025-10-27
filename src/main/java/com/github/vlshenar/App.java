package com.github.vlshenar;

import java.io.*;
import java.sql.*;

/**
 * ORM tutorial
 *
 */
public class App {
    public static void main(String[] args) {
        String login = "Vova";
        String password = "secret";
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";


        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            Statement stat = conn.createStatement();
            stat.executeUpdate("create table Hello (message char(40))");
            stat.executeUpdate("create table employee (ID integer not null, empl_name varchar(20)," +
                    " position varchar(30), primary key (ID))");
            stat.executeUpdate("create sequence employee_SEQ start with 1 increment by 50");
            stat.executeUpdate("insert into Hello values ('Acquaintance with hibernate.')");
            try (ResultSet result = stat.executeQuery("select * from Hello")) {
                while (result.next()) System.out.println(result.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //создание объекта EmployeeManager
        EmployeeManager manager = new EmployeeManager();
        manager.init();
        manager.addEmployee(new Employee("John", "manager"));
        manager.addEmployee(new Employee("Franck", "mechanic"));

        //получение данных из таблицы employee непосредственным sql-запросом
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            Statement stat = conn.createStatement();

            try (ResultSet result = stat.executeQuery("select * from employee")) {
                while (result.next()) System.out.println(result.getString(1) + " " + result.getString(2) +
                        " " + result.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //получение этих же данный с помощью метода get
        System.out.println(manager.getAllEmployees());
    }
}