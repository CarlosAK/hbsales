create table seg_categorias
(
    id    BIGINT IDENTITY (1, 1) NOT NULL PRIMARY KEY,
    cod_categoria VARCHAR (100) NOT NULL,
    nome VARCHAR (100)           NOT NULL,
    id_fornecedor BIGINT         NOT NULL,
    CONSTRAINT fk_produto_fornecedor FOREIGN KEY (id_fornecedor)
    REFERENCES seg_fornecedores (id)

);