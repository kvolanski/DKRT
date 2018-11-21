package br.edu.fapi.dkrt.validators.venda.impl;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.validators.venda.VendaValidator;

public class VendaValidatorImpl implements VendaValidator {

    @Override
    public boolean validaDesconto(int desconto) {
        if (desconto < 0 || desconto >= 100) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validaQuantidadeProdutosPedidoEstoque(PedidoDTO pedidoDTO, ProdutoDTO produtoBusca) {
        if (pedidoDTO.getQuantidade() > produtoBusca.getQtdEstoque()){
            return false;
        }
        return true;
    }

    @Override
    public boolean validaQuantidadePedido(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getQuantidade() <= 0){
            return false;
        }
        return true;
    }
}
