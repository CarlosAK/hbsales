package br.com.hbsis.fornecedor;


import br.com.hbsis.categoria.Categoria;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "seg_fornecedores")

public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "razao_social", unique = true, nullable = false, length = 100)
    private String razaoSocial;
    @Column(name = "cnpj", unique = true, nullable = false, length = 14)
    private String cnpj;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "endereco", nullable = false, length = 100)
    private String endereco;
    @Column(name = "telefone", nullable = false, length = 13)
    private String telefone;
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "fornecedor", targetEntity = Categoria.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private List<Categoria> categoria;

    public Fornecedor(Object id, Object categoria) {

    }

    public List<Categoria> getCategoria() {
        return categoria;

    }

    public void setCategorias(List<Categoria> categorias) {
        this.categoria = categorias;

    }

    public Fornecedor() {

    }

    public void setId(Long Id) {
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Fornecedor(Long id, String razaoSocial, String cnpj, String nome, String endereco, String telefone, String email) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }


}