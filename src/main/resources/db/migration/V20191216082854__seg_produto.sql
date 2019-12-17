create table produtos

(

    id                         INTEGER IDENTITY (1, 1)        PRIMARY KEY,
    id_linha_categoria BIGINT  REFERENCES  seg_categorias(id) NOT NULL,
    cod_produto                VARCHAR(10)                    NOT NULL,
    nome_produto               VARCHAR(200)                   NOT NULL,
    preco_produto              DECIMAL(25,2)                  NOT NULL,
    unidade_cx                 INT(25)                        NOT NULL,
    peso_un                    DECIMAL(25,3)                  NOT NULL,
    unidade_medida             VARCHAR(25)                    NOT NULL,
    validade                   DATE                           NOT NULL

);