CREATE TABLE apostas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    uuid BINARY(16) NOT NULL,
    tipo_jogo VARCHAR(25) NOT NULL,
    dezenas VARCHAR(255) NOT NULL,
    inicio DATE NOT NULL,
    ativo BOOLEAN NOT NULL
);


