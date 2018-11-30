package br.edu.fapi.dkrt.dao.produto;

import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.util.List;

public interface ProdutoDAO {

    int createProduto(ProdutoDTO produtoDTO);

    boolean verificaSeProdutoExiste(String nome);

    ProdutoDTO buscarProdutoPorNome(String nome);

    boolean atualizaProdutoCad(ProdutoDTO produtoDTO);

    ProdutoDTO buscaProdutoPorId (int id);

    List<ProdutoDTO> listarProdutos();

    boolean diminuirEstoque(ProdutoDTO produtoDTO);

    boolean aumentarEstoque(ProdutoDTO produtoDTO);

    List<ProdutoDTO> listarProdutoPesquisa(String sql);

    boolean excluiProduto(int id);

}
