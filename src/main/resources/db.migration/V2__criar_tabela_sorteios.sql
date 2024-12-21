CREATE TABLE sorteios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    uuid VARCHAR(36) NOT NULL,
    numero INT
    tipo_jogo VARCHAR(25) NOT NULL,
    dezenas VARCHAR(255) NOT NULL,
    apuracao DATE NOT NULL,
    municipio BOOLEAN NOT NULL
);



    private Integer numeroSorteio;
    private String tipoJogo;
    private String nomeMunicipioUFSorteio;