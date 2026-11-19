package com.library.library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        if (book.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book id is required");
        }
        if (!bookService.addBook(book)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Book with id " + book.getId() + " already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + id + " not found");
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        Book updated = bookService.updateBook(id, book);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + id + " not found");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.ok("Book deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + id + " not found");
    }
}