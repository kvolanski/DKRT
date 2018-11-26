package br.edu.fapi.dkrt.dao.venda;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.util.List;

public interface VendaDAO {

    int abrirVenda(VendaDTO vendaDTO);

    boolean finalizarVenda(VendaDTO vendaDTO);

    boolean adicionarPedido(PedidoDTO pedidoDTO);

    List<PedidoDTO> listarPedidosVenda(int id);

    List<VendaDTO> listarVendas();

    VendaDTO buscaVenda(int id);

    boolean atualizaStatus(VendaDTO vendaDTO);

    boolean atualizaMotivoCancelamento(VendaDTO vendaDTO);

    boolean retirarPedido(int id);

    PedidoDTO buscaPedido(int id);

}
