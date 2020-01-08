drop index seg_fornecedores.ix_seg_fornecedores_01
drop index seg_fornecedores.ix_seg_fornecedores_02

alter table seg_fornecedores alter column nome VARCHAR (100) NOT NULL

alter table seg_fornecedores alter column endereco VARCHAR (100) NOT NULL

alter table seg_fornecedores alter column telefone VARCHAR (13) NOT NULL

alter table seg_fornecedores alter column email VARCHAR (50) NOT NULL

create unique index ix_seg_fornecedores_01 on seg_fornecedores (cnpj asc);