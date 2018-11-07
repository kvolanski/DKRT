package br.edu.fapi.dkrt.business.produto.impl;

import br.edu.fapi.dkrt.business.produto.ProdutoBusiness;

public class ProdutoBusinessImpl implements ProdutoBusiness {

    @Override
    public boolean verificaNumeroNegativo(int qtd, double precoVenda, double precoCusto) {
        if (qtd < 0 || precoVenda < 0 || precoCusto < 0){
            return false;
        }
        return true;
    }
}
