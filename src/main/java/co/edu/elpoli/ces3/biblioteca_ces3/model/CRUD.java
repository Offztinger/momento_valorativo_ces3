package co.edu.elpoli.ces3.biblioteca_ces3.model;

import co.edu.elpoli.ces3.biblioteca_ces3.dto.DtoBook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD {
    Book create(DtoBook student) throws SQLException;

    public ArrayList<Book> all();

    public Book findById(int id) throws SQLException;

    Book update(Book student) throws SQLException;

    void delete(int studentId) throws SQLException;
}