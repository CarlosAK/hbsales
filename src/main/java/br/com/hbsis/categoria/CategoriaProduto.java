package br.com.hbsis.categoria;

import br.com.hbsis.fornecedor.Fornecedor;
import com.opencsv.bean.CsvBindByPosition;
import javax.persistence.*;


@Entity
@Table(name = "seg_categorias")
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvBindByPosition(position = 0)
    private Long id;
    @Column(name = "cod_categoria", unique = true, nullable = false, length = 100)
    @CsvBindByPosition(position = 1)
    private String codCategoria;
    @Column(name = "nome", unique = true, nullable = false, length = 100)
    @CsvBindByPosition(position = 2)
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id")
    @CsvBindByPosition(position = 3)
    private Fornecedor fornecedor;

    public CategoriaProduto() {

    }

    public CategoriaProduto(String codCategoria, String nome, Fornecedor fornecedor) {
        this.codCategoria = codCategoria;
        this.nome = nome;
        this.fornecedor = fornecedor;

    }

    public CategoriaProduto(Long id, String codCategoria, String nome, Long id_fornecedor, Fornecedor fornecedor) {
        this.id = id;
        this.codCategoria = codCategoria;
        this.nome = nome;
        this.fornecedor = fornecedor;

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

    public void setNome(String nome) {
        this.nome = nome;

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

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", Nome ='" + nome + '\'' +
                ", Fornecedor: ='" + fornecedor + '\'' +
                '}';

    }

}