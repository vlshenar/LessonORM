package com.github.vlshenar;

import jakarta.persistence.*;

import java.util.Date;

//этот класс конфликтует с h2database, если имя таблицы просто user
//но если изменить имя таблицы на, например service_user, то все работает

@Entity @Table(name="service_user")
public class User {
    @Id @GeneratedValue
    @Column(name="id")
    public Integer userId;

    @Column(name="name", unique = true, length = 100)
    public String name;

    @Column(name="level", nullable = true)
    public Integer level;

    @Column(name="created_date", nullable = false)
    public Date createdDate;
}
