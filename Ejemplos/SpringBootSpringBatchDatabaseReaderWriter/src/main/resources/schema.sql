DROP TABLE Personajes IF EXISTS;

CREATE TABLE Personajes  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    nombre VARCHAR(20),
    edad VARCHAR(20),
    genero VARCHAR(20)
);

