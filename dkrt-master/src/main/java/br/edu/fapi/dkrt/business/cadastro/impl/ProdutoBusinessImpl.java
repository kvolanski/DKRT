package br.edu.fapi.dkrt.business.cadastro.impl;

import br.edu.fapi.dkrt.business.cadastro.ProdutoBusiness;
import br.edu.fapi.dkrt.validators.produto.ProdutoValidator;
import br.edu.fapi.dkrt.validators.produto.impl.ProdutoValidatorImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.util.List;

public class ProdutoBusinessImpl implements ProdutoBusiness {
    ProdutoDAO produtoDAO;
    ProdutoValidator produtoValidator;

    public ProdutoBusinessImpl(){
        produtoDAO = new ProdutoDAOImpl();
        produtoValidator = new ProdutoValidatorImpl();
    }

    @Override
    public String cadastrarProduto(ProdutoDTO produtoDTO, String tipo){
        String condicao = "";
        if ("cadastro".equalsIgnoreCase(tipo)) {
            if (produtoDAO.verificaSeProdutoExiste(produtoDTO.getNome())) {
                return "produtoExistente";
            }

            condicao = produtoValidator.verificaProduto(produtoDTO);

            if ("sucesso".equalsIgnoreCase(condicao)) {
                produtoDAO.createProduto(produtoDTO);
            }
        } else if ("alteraProduto".equalsIgnoreCase(tipo)){
            condicao = produtoValidator.verificaProduto(produtoDTO);

            if ("sucesso".equalsIgnoreCase(condicao)) {
                produtoDAO.atualizaProdutoCad(produtoDTO);
            }
        }
        return condicao;
    }

    @Override
    public List<ProdutoDTO> pesquisarProdutoLikeNome(String palavra) {
        String sql = "SELECT produto_id, produto_nome, produto_descricao, produto_precoVenda, produto_precoCusto, produto_qtdEstoque, produto_ativo, produto_dataCadastro, " +
                "produto_dataAlteracao FROM produtos WHERE produto_nome LIKE '%"+palavra+"%'";
        List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutoPesquisa(sql);
        return listaProdutos;
    }

    @Override
    public ProdutoDTO buscarProdutoId(int id) {
        ProdutoDTO produtoDTO = produtoDAO.buscaProdutoPorId(id);
        return produtoDTO;
    }
}
