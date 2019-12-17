package br.com.hbsis.linhaCategoria;


public class LinhaCategoriaDTO {

    private Long idLinhaCategoria;
    private String codLinhaCategoria;
    private String nomeLinhaCategoria;
    private Long categoria;

    public LinhaCategoriaDTO() {

    }

    public LinhaCategoriaDTO(Long idLinhaCategoria, String codLinhaCategoria, String nomeLinhaCategoria, Long categoria) {
        this.idLinhaCategoria = idLinhaCategoria;
        this.codLinhaCategoria = codLinhaCategoria;
        this.nomeLinhaCategoria = nomeLinhaCategoria;
        this.categoria = categoria;
    }

    public static LinhaCategoriaDTO of(LinhaCategoria linhaCategoria) {
        return new LinhaCategoriaDTO(
                linhaCategoria.getIdLinhaCategoria(),
                linhaCategoria.getCodLinhaCategoria(),
                linhaCategoria.getNomeLinhaCategoria(),
                linhaCategoria.getCategoria().getId()
        );

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

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }
}
