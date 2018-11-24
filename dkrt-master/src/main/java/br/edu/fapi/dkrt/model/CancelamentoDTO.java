package br.edu.fapi.dkrt.model;

import br.edu.fapi.dkrt.model.venda.VendaDTO;

public class CancelamentoDTO {
    private int id;
    private String motivo;
    private VendaDTO vendaDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public VendaDTO getVendaDTO() {
        return vendaDTO;
    }

    public void setVendaDTO(VendaDTO vendaDTO) {
        this.vendaDTO = vendaDTO;
    }
}
