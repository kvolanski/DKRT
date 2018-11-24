package br.edu.fapi.dkrt.business.venda.impl;

import br.edu.fapi.dkrt.business.calculator.Calculator;
import br.edu.fapi.dkrt.business.calculator.impl.CalculatorImpl;
import br.edu.fapi.dkrt.business.venda.VendaBusiness;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.dao.venda.impl.VendaDAOImpl;
import br.edu.fapi.dkrt.model.CancelamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.validators.cancelamento.CancelamentoValidator;
import br.edu.fapi.dkrt.validators.cancelamento.impl.CancelamentoValidatorImpl;
import br.edu.fapi.dkrt.validators.venda.VendaValidator;
import br.edu.fapi.dkrt.validators.venda.impl.VendaValidatorImpl;

public class VendaBusinessImpl implements VendaBusiness {
    VendaDAO vendaDAO;
    ProdutoDAO produtoDAO;
    VendaValidator vendaValidator;
    CancelamentoValidator cancelamentoValidator;
    Calculator valorCalculator;

    public VendaBusinessImpl() {
        vendaDAO = new VendaDAOImpl();
        vendaValidator = new VendaValidatorImpl();
        produtoDAO = new ProdutoDAOImpl();
        valorCalculator = new CalculatorImpl();
        cancelamentoValidator = new CancelamentoValidatorImpl();
    }

    @Override
    public int abrirVenda(VendaDTO vendaDTO) {
        if (vendaDTO != null) {
            int id = vendaDAO.abrirVenda(vendaDTO);
            if (id != 0) {
                return id;
            }
        }
        return 0;
    }

    @Override
    public boolean adicionarPedido(PedidoDTO pedidoDTO, ProdutoDTO produtoBusca) {
        if (pedidoDTO != null) {
            if (!vendaValidator.validaQuantidadePedido(pedidoDTO)) {
                return false;
            }

            if (!vendaValidator.validaQuantidadeProdutosPedidoEstoque(pedidoDTO, produtoBusca)) {
                return false;
            }

            if (vendaDAO.adicionarPedido(pedidoDTO, "venda")) {
                produtoBusca.setQtdEstoque(produtoBusca.getQtdEstoque() - pedidoDTO.getQuantidade());
                produtoDAO.diminuirEstoque(produtoBusca);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean finalizarVenda(VendaDTO vendaDTO) {
        if (vendaDTO != null) {
            if (!vendaValidator.validaDesconto(vendaDTO.getDesconto())) {
                return false;
            } else if (vendaDTO.getDesconto() != 0) {
                float desconto = (vendaDTO.getValorTotal() * vendaDTO.getDesconto()) / 100;
                vendaDTO.setValorTotal(vendaDTO.getValorTotal() - desconto);
            }

            vendaDTO.setValorParcelas(valorCalculator.calcularValorParcelas(vendaDTO));

            if (vendaDAO.finalizarVenda(vendaDTO)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean motivoCancelamento(CancelamentoDTO cancelamentoDTO) {
        if (cancelamentoDTO != null) {
            if (!cancelamentoValidator.validaMotivo(cancelamentoDTO.getMotivo())) {
                return false;
            }
            if (vendaDAO.atualizaMotivoCancelamento(cancelamentoDTO)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean retirarPedido(int id) {
        PedidoDTO pedidoDTO = vendaDAO.buscaPedido(id);
        ProdutoDTO produtoDTO = pedidoDTO.getProdutoDTO();
        produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque()+pedidoDTO.getQuantidade());
        if (produtoDAO.aumentarEstoque(produtoDTO)){
            vendaDAO.retirarPedido(pedidoDTO.getId());
            return true;
        }
        return false;
    }
}
