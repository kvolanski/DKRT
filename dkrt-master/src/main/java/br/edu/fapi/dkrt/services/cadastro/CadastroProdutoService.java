package br.edu.fapi.dkrt.services.cadastro;

import br.edu.fapi.dkrt.business.produto.ProdutoBusiness;
import br.edu.fapi.dkrt.business.produto.impl.ProdutoBusinessImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

public class CadastroProdutoService {
    ProdutoDAO produtoDAO;
    ProdutoBusiness produtoBusiness;

    public CadastroProdutoService(){
        produtoDAO = new ProdutoDAOImpl();
        produtoBusiness = new ProdutoBusinessImpl();
    }

    public String cadastrarProduto(ProdutoDTO produtoDTO){
        String condicao = "produtoVazio";
        if (produtoDTO != null){
            condicao = "sucesso";

            if (!produtoBusiness.verificaNumeroNegativo(produtoDTO.getQuantidade(), produtoDTO.getPreco())){
                condicao = "numNegativo ";
            }

            if ("sucesso".equalsIgnoreCase(condicao)){
                produtoDAO.createProduto(produtoDTO);
            }
        }
        return condicao;
    }
}
