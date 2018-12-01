package br.edu.fapi.dkrt.services.impressao;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.io.IOException;

public interface GeraPDFService {

    boolean gerarPdfFichaCliente(ClienteDTO clienteDTO, String caminho, float maiorCompra) throws IOException;

    boolean gerarPdfFichaProduto(ProdutoDTO produtoDTO, String caminho) throws IOException;

}
