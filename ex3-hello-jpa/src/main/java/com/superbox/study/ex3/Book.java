package com.superbox.study.ex3;


import javax.persistence.Entity;

@Entity
public class Book extends Item {

    private String author;
    private String isbn;
}
