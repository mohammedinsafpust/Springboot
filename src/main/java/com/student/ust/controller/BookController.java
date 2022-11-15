package com.student.ust.controller;

import com.student.ust.entity.Book;
import com.student.ust.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Book controller.
 */
@RestController
@Slf4j
public class BookController {
    /**
     * The Book service.
     */
    @Autowired
    BookService bookService;

    /**
     * Get response entity.
     *
     * @param bookId the book id
     * @return the response entity
     */
    @GetMapping("/book/{bookId}")
    public ResponseEntity<Book>
    get(@PathVariable Integer bookId){
        try{
            Book book=bookService.getBookByID(bookId);
            log.debug("log detect"+bookId);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return  new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all response entity.
     *
     * @return the response entity
     */
    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAll(){
        try {
            List<Book> bookList = bookService.getAllBooks();
            return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return  new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete.
     *
     * @param bookId the book id
     */
    @DeleteMapping("/book/{bookId}")
    public void delete(@PathVariable Integer bookId){
        bookService.deleteBook(bookId);
    }

    /**
     * Add.
     *
     * @param book the book
     */
    @PostMapping("/book")
    public void add(@RequestBody Book book){
        bookService.saveBook(book);
    }

    /**
     * Update book.
     *
     * @param book the book
     */
    @PutMapping("/book")
    public void updateBook(@RequestBody Book book){
        bookService.updateBook(book);
    }
}