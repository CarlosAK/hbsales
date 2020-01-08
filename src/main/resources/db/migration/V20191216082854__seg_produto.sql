create table produtos

(

    id                         INTEGER IDENTITY (1, 1)        PRIMARY KEY,
    id_linha_categoria BIGINT  REFERENCES  seg_categorias(id) NOT NULL,
    cod_produto                INTEGER                        NOT NULL,
    nome_produto               VARCHAR(255)                   NOT NULL,
    preco_produto              DECIMAL(25,2)                  NOT NULL,
    unidade_cx                 INTEGER                        NOT NULL,
    peso_un                    DECIMAL(25,3)                  NOT NULL,
    validade                   DATE                           NOT NULL

);