package br.edu.fapi.dkrt.validators.produto.impl;

import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.validators.produto.ProdutoValidator;

public class ProdutoValidatorImpl implements ProdutoValidator {

    @Override
    public String verificaProduto(ProdutoDTO produtoDTO) {
        String condicao = "";
        if (produtoDTO != null) {
            condicao = "sucesso";

            if (!verificaNumeroNegativo(produtoDTO.getQtdEstoque(), produtoDTO.getPrecoVenda(), produtoDTO.getPrecoCusto())) {
                condicao = "numNegativo ";
            }

            if (!verificaSeContemNumero(produtoDTO.getNome())) {
                if ("sucesso".equalsIgnoreCase(condicao)) {
                    condicao = "nomeIncorreto ";
                } else {
                    condicao += "nomeIncorreto ";
                }
            }

            if (produtoDTO.getDescricao() != null) {
                if (!verificaSeContemNumero(produtoDTO.getDescricao())) {
                    if ("sucesso".equalsIgnoreCase(condicao)) {
                        condicao = "descricaoIncorreto ";
                    } else {
                        condicao += "descricaoIncorreto ";
                    }
                }
            }
        }
        return condicao;
    }

    private boolean verificaNumeroNegativo(int qtd, double precoVenda, double precoCusto) {
        if (qtd < 0 || precoVenda < 0 || precoCusto < 0) {
            return false;
        }
        return true;
    }

    private boolean verificaSeContemNumero(String palavra) {
        for (Character c : palavra.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
