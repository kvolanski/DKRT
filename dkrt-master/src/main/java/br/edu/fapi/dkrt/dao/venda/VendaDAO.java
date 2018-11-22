package br.edu.fapi.dkrt.dao.venda;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.util.List;

public interface VendaDAO {

    int abrirVenda(VendaDTO vendaDTO);

    boolean finalizarVenda(VendaDTO vendaDTO);

    boolean adicionarPedido(PedidoDTO pedidoDTO, String tipo);

    List<PedidoDTO> listarPedidosVenda(int id, String tipo);

    List<VendaDTO> listarVendas();

}
