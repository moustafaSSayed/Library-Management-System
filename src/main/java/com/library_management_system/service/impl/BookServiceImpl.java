package com.library_management_system.service.impl;

import com.library_management_system.entity.Book;
import com.library_management_system.repository.BookRepository;
import com.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    @Cacheable(value = "books", unless = "#result == null")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public Book updateBook(Long id, Book updatedBook) {
        Book oldBook = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        oldBook.setTitle(updatedBook.getTitle());
        oldBook.setIsbn(updatedBook.getIsbn());
        oldBook.setAuthor(updatedBook.getAuthor());
        oldBook.setPublicationYear(updatedBook.getPublicationYear());
        return bookRepository.save(oldBook);
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
