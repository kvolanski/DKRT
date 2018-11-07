package br.edu.fapi.dkrt.model.produto;

import java.util.Date;

public class ProdutoDTO {
    private int id;
    private String nome;
    private String descricao;
    private double precoVenda;
    private double precoCusto;
    private int qtdEstoque;
    private int ativo;
    private Date dataCadastroProduto;
    private Date dataAlteracaoProduto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastroProduto() {
        return dataCadastroProduto;
    }

    public void setDataCadastroProduto(Date dataCadastroProduto) {
        this.dataCadastroProduto = dataCadastroProduto;
    }

    public Date getDataAlteracaoProduto() {
        return dataAlteracaoProduto;
    }

    public void setDataAlteracaoProduto(Date dataAlteracaoProduto) {
        this.dataAlteracaoProduto = dataAlteracaoProduto;
    }
}
