create table seg_fornecedores
(
    id  BIGINT IDENTITY (1,1) NOT NULL PRIMARY KEY,
    razao_social VARCHAR (100) NOT NULL,
    cnpj VARCHAR (14)  NOT NULL,
    nome VARCHAR (255) NOT NULL,
    endereco VARCHAR (255) NOT NULL,
    telefone VARCHAR (36) NOT NULL,
    email VARCHAR (255) NOT NULL,

);

    create unique index ix_seg_fornecedores_01 on seg_fornecedores (razao_social asc);
    create unique index ix_seg_fornecedores_02 on seg_fornecedores (cnpj asc);
