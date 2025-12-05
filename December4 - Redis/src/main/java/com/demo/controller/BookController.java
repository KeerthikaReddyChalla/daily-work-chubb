package com.demo.controller;

import com.demo.model.Book;
import com.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> getBooks() {
        return service.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }
}
