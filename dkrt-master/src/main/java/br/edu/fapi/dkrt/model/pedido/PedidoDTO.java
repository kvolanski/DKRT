package br.edu.fapi.dkrt.model.pedido;

import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

public class PedidoDTO {
    private int id;
    private ProdutoDTO produtoDTO;
    private int quantidade;
    private float valorUnitario;
    private float valorTotal;
    private VendaDTO vendaDTO;
    private OrcamentoDTO orcamentoDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProdutoDTO getProdutoDTO() {
        return produtoDTO;
    }

    public void setProdutoDTO(ProdutoDTO produtoDTO) {
        this.produtoDTO = produtoDTO;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public VendaDTO getVendaDTO() {
        return vendaDTO;
    }

    public void setVendaDTO(VendaDTO vendaDTO) {
        this.vendaDTO = vendaDTO;
    }

    public OrcamentoDTO getOrcamentoDTO() {
        return orcamentoDTO;
    }

    public void setOrcamentoDTO(OrcamentoDTO orcamentoDTO) {
        this.orcamentoDTO = orcamentoDTO;
    }
}
