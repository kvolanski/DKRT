package br.edu.fapi.dkrt.model.venda;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

import java.util.Date;

public class VendaDTO {
    private int id;
    private float valorTotal;
    private String formaDePagamento;
    private int parcelas;
    private float valorParcelas;
    private String status;
    private int desconto;
    private Date dataDeVenda;
    private String motivoCancelamento;
    private String vendaOrcamento;
    private ClienteDTO clienteDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public float getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(float valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public Date getDataDeVenda() {
        return dataDeVenda;
    }

    public void setDataDeVenda(Date dataDeVenda) {
        this.dataDeVenda = dataDeVenda;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getVendaOrcamento() {
        return vendaOrcamento;
    }

    public void setVendaOrcamento(String vendaOrcamento) {
        this.vendaOrcamento = vendaOrcamento;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }
}
