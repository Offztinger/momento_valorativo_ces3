package co.edu.elpoli.ces3.biblioteca_ces3.controller;

import co.edu.elpoli.ces3.biblioteca_ces3.dto.DtoBook;
import co.edu.elpoli.ces3.biblioteca_ces3.model.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class CtrBook {

    private Book modelBook;

    public CtrBook(){
        modelBook = new Book();
    }

    public DtoBook addBook(DtoBook Book){
        try {
            Book newBook = modelBook.create(Book);
            return new DtoBook(newBook.getId(), newBook.getBookname(), newBook.getAuthor());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DtoBook> getAllBooks() {
        try {
            ArrayList<Book> Books = modelBook.all(); // Llama al método 'all' del modelo
            ArrayList<DtoBook> dtoBooks = new ArrayList<>();

            for (Book Book : Books) {
                DtoBook dtoBook = new DtoBook(
                        Book.getId(),
                        Book.getBookname(), //Se cambia por getBookname Document
                        Book.getAuthor() //Se cambia por getAuthor Name
                );
                dtoBooks.add(dtoBook);
            }

            return dtoBooks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DtoBook getBookById(int BookId) {
        try {
            Book Book = modelBook.findById(BookId);
            if (Book != null) {
                return new DtoBook(Book.getId(), Book.getBookname(), Book.getAuthor());
            } else {
                throw new RuntimeException("No se encuentra el libro");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DtoBook updateBook(int BookId, DtoBook updatedBook) {
        try {
            Book Book = new Book(
                    BookId,
                    updatedBook.getBookname(),
                    updatedBook.getAuthor()

            );

            Book updated = modelBook.update(Book);
            return new DtoBook(updated.getId(), updated.getBookname(), updated.getAuthor());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(int BookId) {
        try {
            modelBook.delete(BookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
   }
}


}