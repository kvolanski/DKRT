package br.edu.fapi.dkrt.business.produto.impl;

import br.edu.fapi.dkrt.business.produto.ProdutoBusiness;

public class ProdutoBusinessImpl implements ProdutoBusiness {

    @Override
    public boolean verificaNumeroNegativo(int qtd, double preco) {
        if (qtd < 0 || preco < 0){
            return false;
        }
        return true;
    }
}
