package br.edu.fapi.dkrt.business.orcamento;

import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

public interface OrcamentoBusiness {

    int abrirOrcamento(OrcamentoDTO orcamentoDTO);

    boolean adicionarPedido(PedidoDTO pedidoDTO, ProdutoDTO produtoBusca);

    boolean retirarPedido(int id);

    boolean finalizarOrcamento(OrcamentoDTO orcamentoDTO);

}
