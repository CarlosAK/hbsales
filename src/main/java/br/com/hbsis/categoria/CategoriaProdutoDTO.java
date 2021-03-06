package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;


public class CategoriaProdutoDTO {
    private Fornecedor fornecedor;
    private Long id;
    private String codCategoria;
    private String nome;
    private Long fornecedorId;

    public CategoriaProdutoDTO() {

    }

    public CategoriaProdutoDTO(String codCategoria, String nome, Fornecedor fornecedor) {
        this.codCategoria = codCategoria;
        this.nome = nome;
        this.fornecedor = fornecedor;

    }

    public CategoriaProdutoDTO(Long id, String codCategoria, String nome, Fornecedor fornecedor) {
        this.id = id;
        this.codCategoria = codCategoria;
        this.nome = nome;
        this.fornecedor = fornecedor;

    }


    public static CategoriaProdutoDTO of(CategoriaProduto categoriaProduto) {
        return new CategoriaProdutoDTO(categoriaProduto.getId(),
                categoriaProduto.getCodCategoria(),
                categoriaProduto.getNome(),
                categoriaProduto.getFornecedor());


    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getNome() {
        return nome;

    }

    public String getCodCategoria() {
        return codCategoria;

    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;

    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public Fornecedor getFornecedor() {
        return fornecedor;

    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;

    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", Nome ='" + nome + '\'' +
                ", Fornecedor: ='" + fornecedor + '\'' +
                '}';

    }

    public Long getIdFornecedor() {
        return fornecedorId;

    }
}