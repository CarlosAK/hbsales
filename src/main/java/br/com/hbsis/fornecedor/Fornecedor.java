package br.com.hbsis.fornecedor;

import javax.persistence.*;

@Entity
@Table(name = "seg_fornecedor")
class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Razão Social", unique = true, nullable = false, length = 100)
    private String RazaoSocial;
    @Column(name = "CNPJ", unique = true, nullable = false, length = 100)
    private String CNPJ;
    @Column(name = "Nome Fantasia")
    private String NomeFantasia;
    @Column(name = "Endereço")
    private String Endereco;
    @Column(name = "Telefone")
    private double Telefone;
    @Column(name = "Email")
    private String Email;

    public Fornecedor(){
    }

    public Fornecedor(String nomeFantasia, String CNPJ){
        this.NomeFantasia = nomeFantasia;
        this.CNPJ = CNPJ;
    }

    public Fornecedor(String razaoSocial, String cnpj, String nomeFantasia, String endereco, double telefone, String email) {
    }

    public static FornecedorDTO of(Fornecedor fornecedor) {
        return null;
    }

    public Long getId(){
        return id;
    }

    public String getRazaoSocial(){
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial){
        this.RazaoSocial = razaoSocial;
    }

    public String getCNPJ(){
        return CNPJ;
    }

    public void setCNPJ(String CNPJ){
        this.CNPJ = CNPJ;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia){
        this.NomeFantasia = nomeFantasia;
    }

    public String getEndereco(){
        return Endereco;
    }

    public void setEndereco(String endereco){
        this.Endereco = endereco;
    }

    public double getTelefone(){
        return Telefone;
    }

    public void setTelefone(double telefone){
        this.Telefone = telefone;
    }

    public String getEmail(){
        return Email;
    }
    public void setEmail(String email){
        this.Email = email;
    }



}
