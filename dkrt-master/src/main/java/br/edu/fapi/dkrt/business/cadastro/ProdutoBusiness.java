package br.edu.fapi.dkrt.business.cadastro;

import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

public interface ProdutoBusiness {

    String cadastrarProduto(ProdutoDTO produtoDTO, String tipo);

}
