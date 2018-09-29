package br.edu.fapi.dkrt.produto;

import java.util.Date;

public class ProdutoDTO {

    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private Date dataCadastroProduto;
    private Date dataAlteracaoProduto;

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
