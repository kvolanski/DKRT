package br.edu.fapi.dkrt.business.impressao;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

public interface ImpressaoBusiness {

    boolean gerarPdfFichaCliente(ClienteDTO clienteDTO);
}
