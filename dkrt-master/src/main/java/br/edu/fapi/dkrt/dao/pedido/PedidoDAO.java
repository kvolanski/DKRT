package br.edu.fapi.dkrt.dao.pedido;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;

public interface PedidoDAO {

    PedidoDTO buscaPedido(int id, String tipo);

    boolean retirarPedido(int id);
}
