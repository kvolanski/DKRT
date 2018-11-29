package br.edu.fapi.dkrt.services.impressao;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

import java.io.IOException;

public interface GeraPDFService {

    boolean gerarPdfFichaCliente(ClienteDTO clienteDTO) throws IOException;

}
