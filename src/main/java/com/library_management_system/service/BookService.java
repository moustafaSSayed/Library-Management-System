package com.library_management_system.service;

import com.library_management_system.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public Book getBookById(Long id);
    public Book addBook(Book book);
    public Book updateBook(Long id, Book updatedBook);
    public void deleteBook(Long id);
}
