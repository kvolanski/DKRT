package br.edu.fapi.dkrt.model.cliente;

import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;

import java.util.Date;

public class ClienteDTO {
    private String nome;
    private String nomeSocial;
    private String cpf;
    private Date dtNascimento;
    private String telefone;
    private boolean ativo;
    private Date dataCadastroCliente;
    private Date dataAlteracaoCliente;
    private EnderecoDTO enderecoDTO;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastroCliente() {
        return dataCadastroCliente;
    }

    public void setDataCadastroCliente(Date dataCadastroCliente) {
        this.dataCadastroCliente = dataCadastroCliente;
    }

    public Date getDataAlteracaoCliente() {
        return dataAlteracaoCliente;
    }

    public void setDataAlteracaoCliente(Date dataAlteracaoCliente) {
        this.dataAlteracaoCliente = dataAlteracaoCliente;
    }

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
