CREATE DATABASE BIBLIOTECA;
USE BIBLIOTECA;

CREATE TABLE `biblioteca`.`libros` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `bookname` VARCHAR(45) NOT NULL,
                                       `author` VARCHAR(45) NOT NULL,
                                       PRIMARY KEY (`id`)
);
