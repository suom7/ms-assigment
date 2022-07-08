package com.ms.bookservice.services;

import com.ms.bookservice.entites.Book;
import com.ms.bookservice.exceptions.InvalidArgumentException;
import com.ms.bookservice.exceptions.ObjectNotFoundException;
import com.ms.bookservice.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book entity) {
        if (Objects.isNull(entity)
                || Objects.isNull(entity.getAuthor())
                || Objects.isNull(entity.getCategory())
                || Objects.isNull(entity.getDescription())
                || Objects.isNull(entity.getTitle())) {
            throw new InvalidArgumentException("Missing mandatory fields");
        }

        this.bookRepository.save(entity);
    }

    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Book not found"));
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }
}
