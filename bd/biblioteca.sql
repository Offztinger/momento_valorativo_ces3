CREATE DATABASE BIBLIOTECA;
USE BIBLIOTECA;

CREATE TABLE `biblioteca`.`libros` (
    `id_libro` INT NOT NULL AUTO_INCREMENT,
    `titulo_libro` VARCHAR(45) NOT NULL,
    `autor_libro` VARCHAR(45) NOT NULL,
    `paginas_libro` VARCHAR(45) NULL,
    PRIMARY KEY (`id_libro`)
);
