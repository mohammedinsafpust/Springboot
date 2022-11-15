package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Book.
 */
@Entity
@Data
@Table(name="book_ust_details_identitymappedbytest")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private int isbnNo;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    // @Column(name="book_id")
    @ManyToOne
    private Student student;



}
