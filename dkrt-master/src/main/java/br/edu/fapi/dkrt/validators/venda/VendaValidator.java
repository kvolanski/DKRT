package br.edu.fapi.dkrt.validators.venda;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

public interface VendaValidator {

    boolean validaDesconto(int desconto);

    boolean validaQuantidadeProdutosPedidoEstoque(PedidoDTO pedidoDTO, ProdutoDTO produtoBusca);

    boolean validaQuantidadePedido(PedidoDTO pedidoDTO);

}
