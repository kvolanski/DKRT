package br.edu.fapi.dkrt.business.calculator;

import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.util.List;

public interface Calculator{

    float calcularValorTotal(List<PedidoDTO> listaPedido);

    float calcularValorParcelas(VendaDTO vendaDTO);
}
