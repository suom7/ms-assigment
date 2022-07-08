package com.ms.bookservice.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "book")
public class Book {
    /**
     * Auto generate id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Author of the book
     */
    @Column(name = "author")
    private String author;
    /**
     * Book title
     */
    @Column(name = "title")
    private String title;
    /**
     * Category
     */
    @Column(name = "category")
    private String category;
    /**
     * Description
     */
    @Column(name = "description")
    private String description;
}
