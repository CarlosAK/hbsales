drop index linha_categorias.ix_linha_categorias_01

alter table linha_categorias drop column cat_linha_categorias

alter table linha_categorias alter column cod_linha_categoria varchar (10) NOT NULL

alter table linha_categorias alter column nome_linha_categoria varchar (50) NOT NULL

create unique index ix_linha_categorias_01 on linha_categorias (cod_linha_categoria asc);