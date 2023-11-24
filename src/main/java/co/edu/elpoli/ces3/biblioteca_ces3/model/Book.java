package co.edu.elpoli.ces3.biblioteca_ces3.model;
import co.edu.elpoli.ces3.biblioteca_ces3.dto.DtoBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Book extends Konnection implements CRUD{
    public int id;

    protected String author;

    private String bookname;

    public Book(int id, String author, String bookname){
        this.id = id;
        this.author = author;
        this.bookname = bookname;
    }

    public Book(String author){
        this.author = author;
    }

    public Book() {
    }

    public int getId(){
        return this.id;
    }


    private void setId(int id){
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Override
    public String toString() {
        return "El libro tiene como titulo " + this.bookname + " y su autor es " + this.author;
    }

    @Override
    public Book create(DtoBook student) throws SQLException {
        Connection cnn = this.getConexion();
        if(cnn != null) {
            String sql = "INSERT INTO user(author, bookname) VALUES('"+student.getAuthor()+"', '"+student.getBookname()+"')";
            this.author = student.getAuthor();
            this.bookname = student.getBookname();
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                this.id = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                cnn.close();
            }
            return this;
        }
        return null;
    }

    @Override
    public ArrayList<Book> all() {
        Connection cnn = this.getConexion();
        ArrayList<Book> books = new ArrayList<>();

        if (cnn != null) {
            String sql = "SELECT id,author,bookname FROM user";
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String bookname = rs.getString("bookname");
                    String author = rs.getString("author");
                    Book book = new Book(id, author, bookname);
                    books.add(book);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (cnn != null) {
                        cnn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return books;
        }
        return null;
    }



@Override
    public Book findById(int studentId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "SELECT id,author,bookname FROM user WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String author = rs.getString("author");
                        String bookname = rs.getString("bookname");
                        return new Book(id, author, bookname);
                    } else {
                        return null;
                    }
                }
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return null;
    }

    @Override
    public Book update(Book book) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "UPDATE user SET author = ?, bookname = ? WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setString(1, book.getAuthor());
                stmt.setString(2, book.getBookname());
                stmt.setInt(3, book.getId());
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return book;
    }

    @Override
    public void delete(int studentId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "DELETE FROM user WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
    }


}
