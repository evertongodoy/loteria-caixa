CREATE TABLE resultados (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    uuid BINARY(16) NOT NULL,
    numero INT NOT NULL,
    tipo_jogo VARCHAR(25) NOT NULL,
    dezenas VARCHAR(255) NOT NULL,
    apuracao DATE NOT NULL,
    municipio BOOLEAN NOT NULL
);


