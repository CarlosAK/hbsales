package br.com.hbsis.cadastro;

import java.time.LocalDate;

public class ProdutoDTO {

    private Long idProduto;
    private String codProduto;
    private String nomeProduto;
    private double precoProduto;
    private int unidadeCx;
    private double pesoUn;
    private String unidadeMedida;
    private LocalDate validade;
    private long linhaCategoria;

    public ProdutoDTO() {

    }

    public ProdutoDTO(Long idProduto, String codProduto, String nomeProduto, double precoProduto, int unidadeCx, double pesoUn, String unidadeMedida, LocalDate validade, long linhaCategoria) {
        this.idProduto = idProduto;
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.unidadeCx = unidadeCx;
        this.pesoUn = pesoUn;
        this.unidadeMedida = unidadeMedida;
        this.validade = validade;
        this.linhaCategoria = linhaCategoria;
    }

    public static ProdutoDTO of(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getCodProduto(),
                produto.getNomeProduto(),
                produto.getPrecoProduto(),
                produto.getUnidadeCx(),
                produto.getPesoUn(),
                produto.getUnidadeMedida(),
                produto.getValidade(),
                produto.getLinhaCategoria().getIdLinhaCategoria());

    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
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

    public long getLinhaCategoria() {
        return linhaCategoria;
    }

    public void setLinhaCategoria(long linhaCategoria) {
        this.linhaCategoria = linhaCategoria;
    }

}