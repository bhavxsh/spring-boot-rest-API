package com.library.library;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final Map<Integer, Book> books = new LinkedHashMap<>();

    public boolean addBook(Book book) {
        if (books.containsKey(book.getId())) {
            return false;
        }
        books.put(book.getId(), book);
        return true;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book getBookById(Integer id) {
        return books.get(id);
    }

    public Book updateBook(Integer id, Book updated) {
        Book existing = books.get(id);
        if (existing == null) {
            return null;
        }
        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        existing.setCategory(updated.getCategory());
        existing.setAvailableCopies(updated.getAvailableCopies());
        return existing;
    }

    public boolean deleteBook(Integer id) {
        return books.remove(id) != null;
    }
}