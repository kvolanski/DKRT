package br.edu.fapi.dkrt.business.estoque.impl;

import br.edu.fapi.dkrt.business.estoque.EstoqueBusiness;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.validators.produto.ProdutoValidator;
import br.edu.fapi.dkrt.validators.produto.impl.ProdutoValidatorImpl;

public class EstoqueBusinessImpl implements EstoqueBusiness {

    ProdutoValidator produtoValidator;
    ProdutoDAO produtoDAO;

    public EstoqueBusinessImpl() {
        produtoValidator = new ProdutoValidatorImpl();
        produtoDAO = new ProdutoDAOImpl();
    }

    @Override
    public boolean atualizaProduto(ProdutoDTO produtoDTO) {
        if (produtoDTO != null) {
            String sucesso = produtoValidator.verificaProduto(produtoDTO);
            if ("sucesso".equalsIgnoreCase(sucesso)) {
                produtoDAO.atualizaProdutoCad(produtoDTO);
                return true;
            }
        }
        return false;
    }
}
