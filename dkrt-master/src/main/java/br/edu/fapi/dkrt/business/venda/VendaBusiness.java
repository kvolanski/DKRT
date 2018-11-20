package br.edu.fapi.dkrt.business.venda;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

public interface VendaBusiness {

    int abrirVenda(VendaDTO vendaDTO);

    boolean adicionarPedido(PedidoDTO pedidoDTO);

    boolean finalizarVenda(VendaDTO vendaDTO);

}
