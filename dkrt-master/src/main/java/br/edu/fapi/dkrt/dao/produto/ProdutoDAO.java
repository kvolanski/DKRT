package br.edu.fapi.dkrt.dao.produto;

import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

public interface ProdutoDAO {

    int createProduto(ProdutoDTO produtoDTO);

    boolean verificaSeProdutoExiste(String nome);

    ProdutoDTO buscarProdutoPorNome(String nome);

    boolean atualizaProdutoCad(ProdutoDTO produtoDTO);

}
