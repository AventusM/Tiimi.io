/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.domain;

import java.sql.Date;

/**
 *
 * @author tminka
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String ISBN;
    private String tags;
    private Date time;

    public Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.ISBN = isbn;
    }

    public Book(int id, String title, String author, String ISBN, String tags, Date time) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.tags = tags;
        this.time = time;
    }

    public Book(String title, String author, String ISBN, String tags, Date time) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.tags = tags;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public Date getTime() {
        return time;
    }

    public String getTags() {
        return tags;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
