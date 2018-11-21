package br.edu.fapi.dkrt.business.calculator.impl;

import br.edu.fapi.dkrt.business.calculator.Calculator;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.util.List;

public class CalculatorImpl implements Calculator {

    @Override
    public float calcularValorTotal(List<PedidoDTO> listaPedido) {
        float soma = 0;
        for (PedidoDTO pedido : listaPedido){
            soma += pedido.getValorTotal();
        }
        return soma;
    }

    @Override
    public float calcularValorParcelas(VendaDTO vendaDTO) {
        float valor = vendaDTO.getValorTotal()/vendaDTO.getParcelas();
        return valor;
    }
}
