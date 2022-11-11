package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Lazy
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public Book getBookByID(Integer BookId) {
        return bookRepository.findById(BookId).orElse(null);
    }

    public Book saveBook(Book book) {
        book.setCreateDate(LocalDateTime.now());
        book.setModifyDate(LocalDateTime.now());
        return bookRepository.save(book);

    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
    public Book updateBook(Book book){
        Book updateBook= bookRepository.findById(book.getBookId()).orElseThrow(()->new NoSuchElementException());
        updateBook.setBookAuthor(book.getBookAuthor());
        updateBook.setBookName(book.getBookName());
        updateBook.setIsbnNo(book.getIsbnNo());
        updateBook.setModifyDate(LocalDateTime.now());
        bookRepository.save(updateBook);
        return updateBook;
    }
}
