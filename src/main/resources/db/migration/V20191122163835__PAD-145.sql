create table seg_fornecedor
(
    id       BIGINT IDENTITY(1,1)       NOT NULL,
    RazaoSocial     VARCHAR (100)       NOT NULL,
    CNPJ            VARCHAR (14)        NOT NULL,
    NomeFantasia    VARCHAR (100)       NOT NULL,
    Endereco        VARCHAR (50)        NOT NULL,
    Telefone        VARCHAR (15)        NOT NULL,
    Email           VARCHAR (100)       NOT NULL,

);

