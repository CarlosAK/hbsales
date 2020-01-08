package br.com.hbsis.linhaCategoria;


import br.com.hbsis.categoria.Categoria;

import javax.persistence.*;

@Entity
@Table(name = "linha_categoria")

public class LinhaCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idLinhaCategoria;

    @Column(name = "cod_linha_categoria", nullable = false, length = 10)
    private String codLinhaCategoria;

    @Column(name = "nome_linha_categoria", nullable = false, length = 50)
    private String nomeLinhaCategoria;

    @ManyToOne
    @JoinColumn(name = "fk_id_categoria", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    public LinhaCategoria() {

    }

    public LinhaCategoria(String codLinhaCategoria, String catLinha, String nomeLinhaCategoria) {
        this.codLinhaCategoria = codLinhaCategoria;
        this.nomeLinhaCategoria = nomeLinhaCategoria;
    }

    public Long getIdLinhaCategoria() {
        return idLinhaCategoria;
    }

    public void setIdLinhaCategoria(Long idLinhaCategoria) {
        this.idLinhaCategoria = idLinhaCategoria;
    }

    public String getCodLinhaCategoria() {
        return codLinhaCategoria;
    }

    public void setCodLinhaCategoria(String codLinhaCategoria) {
        this.codLinhaCategoria = codLinhaCategoria;
    }

    public String getNomeLinhaCategoria() {
        return nomeLinhaCategoria;
    }

    public void setNomeLinhaCategoria(String nomeLinhaCategoria) {
        this.nomeLinhaCategoria = nomeLinhaCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
