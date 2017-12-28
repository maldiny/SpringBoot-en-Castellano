DROP TABLE personajes IF EXISTS;

CREATE TABLE Personajes  (
    personaje_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    nombre VARCHAR(20),
    edad int,
    genero VARCHAR(20)
);