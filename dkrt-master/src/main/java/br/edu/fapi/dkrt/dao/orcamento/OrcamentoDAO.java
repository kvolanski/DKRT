package br.edu.fapi.dkrt.dao.orcamento;

import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;

public interface OrcamentoDAO {

    int abrirOrcamento(OrcamentoDTO orcamentoDTO);

    boolean adicionarPedido(PedidoDTO pedidoDTO);

}
