package br.edu.fapi.dkrt.dao.orcamento;

import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;

import java.util.List;

public interface OrcamentoDAO {

    int abrirOrcamento(OrcamentoDTO orcamentoDTO);

    boolean adicionarPedido(PedidoDTO pedidoDTO);

    List<PedidoDTO> listarPedidosOrcamento(int id);

    boolean finalizarOrcamento(OrcamentoDTO orcamentoDTO);

    List<OrcamentoDTO> listarOrcamentos();

    boolean excluirOrcamento(int id);

    boolean adicionaIdVendaOrcamento(int idOrcamento, int idVenda);

    OrcamentoDTO buscarOrcamento(int id);

    boolean atualizarStatusOrcamento(OrcamentoDTO orcamentoDTO);

}
