CREATE TABLE produto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(30),
    preco DECIMAL(10,2),
    quantidade INT,
    lote VARCHAR(15),
    codigo_barra VARCHAR(50)
);