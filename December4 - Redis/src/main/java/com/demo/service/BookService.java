package com.demo.service;

import com.demo.model.Book;
import com.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository repo;

    @Cacheable(value = "books")
    public List<Book> getAllBooks() {
        simulateDelay();
        return repo.findAll();
    }

    @CacheEvict(value = "books", allEntries = true)
    public Book addBook(Book book) {
        return repo.save(book);
    }

    private void simulateDelay() {
        try { Thread.sleep(3000); } catch (Exception ignored) {}
    }
}
