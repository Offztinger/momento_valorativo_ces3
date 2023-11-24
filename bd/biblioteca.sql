CREATE DATABASE BIBLIOTECA;
USE BIBLIOTECA;

CREATE TABLE `biblioteca`.`libros` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `bookname` VARCHAR(45) NOT NULL,
    `author` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO biblioteca.libros (bookname, author) VALUES ('El Principito', 'Antoine de Saint-Exupéry');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('Cien años de soledad', 'Gabriel García Márquez');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('El Quijote', 'Miguel de Cervantes');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('El Señor de los Anillos', 'J.R.R. Tolkien');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('Harry Potter y la piedra filosofal', 'J.K. Rowling');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('Las Crónicas de Narnia', 'C.S. Lewis');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('El Hobbit', 'J.R.R. Tolkien');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('El diario de Ana Frank', 'Ana Frank');
INSERT INTO biblioteca.libros (bookname, author) VALUES ('Orgullo y prejuicio', 'Jane Austen');