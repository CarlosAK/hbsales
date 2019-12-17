package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;
import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.*;

@Entity
@Table(name = "seg_categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvBindByPosition(position = 0)
    private Long id;
    @CsvBindByPosition(position = 1)
    @Column(name = "nome_categoria", unique = true, nullable = false, length = 50)
    private String nomeCategoria;
    @CsvBindByPosition(position = 2)
    @Column(name = "cod_categoria", unique = true, nullable = false, length = 10)
    private String codCategoria;
    @ManyToOne
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id")
    @CsvBindByPosition(position = 3)
    private Fornecedor fornecedor;

    public Categoria() {
    }

    public Categoria(Long id, String nomeCategoria, String codCategoria, Fornecedor fornecedor) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.codCategoria = codCategoria;
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


}