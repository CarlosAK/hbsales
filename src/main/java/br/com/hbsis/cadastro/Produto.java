package br.com.hbsis.cadastro;


import br.com.hbsis.linhaCategoria.LinhaCategoria;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_produto", unique = true, nullable = false, length = 10)
    private String codProduto;

    @Column(name = "nome_produto", unique = true, nullable = false, length = 200)
    private String nomeProduto;

    @Column(name = "preco_produto", nullable = false, length = 25)
    private double precoProduto;

    @Column(name = "unidade_cx", nullable = false, length = 25)
    private int unidadeCx;

    @Column(name = "peso_un", nullable = false, length = 25)
    private double pesoUn;

    @Column(name = "unidade_medida", nullable = false, length = 25)
    private String unidadeMedida;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "validade")
    private LocalDate validade;

    @ManyToOne
    @JoinColumn(name = "id_linha_categoria", referencedColumnName = "id")

    private LinhaCategoria linhaCategoria;

    public Produto() {

    }

    public Produto(Long id, String codProduto, String nomeProduto, double precoProduto, int unidadeCx, double pesoUn, String unidadeMedida, LocalDate validade, LinhaCategoria linhaCategoria) {
        this.id = id;
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.unidadeCx = unidadeCx;
        this.pesoUn = pesoUn;
        this.unidadeMedida = unidadeMedida;
        this.validade = validade;
        this.linhaCategoria = linhaCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getUnidadeCx() {
        return unidadeCx;
    }

    public void setUnidadeCx(int unidadeCx) {
        this.unidadeCx = unidadeCx;
    }

    public double getPesoUn() {
        return pesoUn;
    }

    public void setPesoUn(double pesoUn) {
        this.pesoUn = pesoUn;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public LinhaCategoria getLinhaCategoria() {
        return linhaCategoria;
    }

    public void setLinhaCategoria(LinhaCategoria linhaCategoria) {
        this.linhaCategoria = linhaCategoria;
    }

}
