package com.springboot.learnjpaandhibernate.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity //cria tabela no banco dedados automaticamente
public class Course {
    @Id //primary key
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="author")
    private String author;

    //has to have an empty constructor and setters so the springJdbcTemplate.queryForObject works
    public Course(){

    }
    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Course[id="+id+", name="+name+", author="+author+"]";
    }
}
