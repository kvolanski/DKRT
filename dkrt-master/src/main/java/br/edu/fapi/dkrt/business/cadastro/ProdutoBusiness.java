package br.edu.fapi.dkrt.business.cadastro;

import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.util.List;

public interface ProdutoBusiness {

    String cadastrarProduto(ProdutoDTO produtoDTO, String tipo);

    List<ProdutoDTO> pesquisarProdutoLikeNome(String palavra);

    ProdutoDTO buscarProdutoId(int id);

    boolean excluirProduto(int id);

}
