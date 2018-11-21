package br.edu.fapi.dkrt.business.venda;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

public interface VendaBusiness {

    int abrirVenda(VendaDTO vendaDTO);

    boolean adicionarPedido(PedidoDTO pedidoDTO, ProdutoDTO produtoBusca);

    boolean finalizarVenda(VendaDTO vendaDTO);

}
