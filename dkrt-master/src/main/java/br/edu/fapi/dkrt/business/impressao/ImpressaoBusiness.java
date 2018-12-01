package br.edu.fapi.dkrt.business.impressao;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

public interface ImpressaoBusiness {

    boolean gerarPdfFichaCliente(ClienteDTO clienteDTO);

    boolean gerarPdfFichaProduto(ProdutoDTO produtoDTO);
}
