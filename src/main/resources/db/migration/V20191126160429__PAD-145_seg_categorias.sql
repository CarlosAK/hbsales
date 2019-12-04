create table seg_categorias
(
    id    BIGINT   IDENTITY (1, 1)  NOT NULL PRIMARY KEY,
    nome_categoria VARCHAR (100)    NOT NULL,
    cod_categoria  VARCHAR (100)    NOT NULL,
    id_fornecedor BIGINT            NOT NULL,
    FOREIGN KEY (id_fornecedor)  REFERENCES seg_fornecedores (id)

);