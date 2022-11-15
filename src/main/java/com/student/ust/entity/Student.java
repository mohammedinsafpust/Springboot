package com.student.ust.entity;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
/**
 * The type Student.
 */
@Entity
@Data
@Table(name="Student_ustbatch_identitymappedbytestest")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int student_id;
    private String name;
    private int age;
    private int roll_no;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    @OneToOne
    @JoinColumn(name="book_id")
    private  Book book;
    /**
     * The Email.
     */
    @NotNull
    private String email;
    /**
     * The Password.
     */
    @NotNull
    private String password;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
    private Set<Book> bookSet;
}
