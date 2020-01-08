create table linha_categorias
(
    id      BIGINT          IDENTITY (1,1) NOT NULL PRIMARY KEY,
    cod_linha_categoria     VARCHAR  (10)  NOT NULL,
    nome_linha_categoria    VARCHAR  (50)  NOT NULL,
    fk_id_categoria         BIGINT         NOT NULL,

);

create unique index ix_linha_categorias_01 on linha_categorias (cod_linha_categoria);