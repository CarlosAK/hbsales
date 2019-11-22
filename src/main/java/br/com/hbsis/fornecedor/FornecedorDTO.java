package br.com.hbsis.fornecedor;

public class FornecedorDTO {

    private Long id;
    private String RazaoSocial;
    private String CNPJ;
    private String NomeFantasia;
    private String Endereco;
    private double Telefone;
    private String Email;

    public FornecedorDTO() {
    }

    public FornecedorDTO(Long id, String razaoSocial, String CNPJ, String nomeFantasia, String endereco, double telefone, String email) {
        this.id = id;
        this.RazaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.NomeFantasia = nomeFantasia;
        this.Endereco = endereco;
        this.Telefone = telefone;
        this.Email = email;
    }

    public static FornecedorDTO of(Fornecedor fornecedor){
        return new FornecedorDTO(
               fornecedor.getId(),
               fornecedor.getRazaoSocial(),
               fornecedor.getCNPJ(),
               fornecedor.getNomeFantasia(),
               fornecedor.getEndereco(),
               fornecedor.getTelefone(),
               fornecedor.getEmail()

     );

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        NomeFantasia = nomeFantasia;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public double getTelefone() {
        return Telefone;
    }

    public void setTelefone(double telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
