package br.edu.fapi.dkrt.business.estoque;

import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

public interface EstoqueBusiness {

    boolean atualizaProduto(ProdutoDTO produtoDTO);

}
