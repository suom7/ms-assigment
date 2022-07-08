package com.ms.bookservice.web;

import com.ms.bookservice.entites.Book;
import com.ms.bookservice.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        log.info("Book {}", book);
        service.save(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
