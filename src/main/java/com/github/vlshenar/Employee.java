package com.github.vlshenar;

import jakarta.persistence.*;

@Entity @Table(name="employee")
public class Employee {
    @Id @GeneratedValue
    @Column(name="ID")
    private Integer emplId;

    @Column(name="empl_name", unique = true, length = 20)
    private String name;

    @Column(name="position", length = 30, nullable = true)
    private String position;

    //constructors
    public Employee() {

    }

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }
    //getters
    public Integer getEmplId() {
        return emplId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    //setter
    public void setPosition(String position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setEmplId(Integer id) {
        this.emplId = id;
    }

    public String toString() {
        return emplId + " " + name + " " + position;
    }
}
