package br.edu.fapi.dkrt.business.venda.impl;

import br.edu.fapi.dkrt.business.calculator.Calculator;
import br.edu.fapi.dkrt.business.calculator.impl.CalculatorImpl;
import br.edu.fapi.dkrt.business.venda.VendaBusiness;
import br.edu.fapi.dkrt.dao.pedido.PedidoDAO;
import br.edu.fapi.dkrt.dao.pedido.impl.PedidoDAOImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.dao.venda.impl.VendaDAOImpl;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.validators.cancelamento.CancelamentoValidator;
import br.edu.fapi.dkrt.validators.cancelamento.impl.CancelamentoValidatorImpl;
import br.edu.fapi.dkrt.validators.venda.PedidoValidator;
import br.edu.fapi.dkrt.validators.venda.impl.PedidoValidatorImpl;

import java.util.List;

public class VendaBusinessImpl implements VendaBusiness {
    VendaDAO vendaDAO;
    ProdutoDAO produtoDAO;
    PedidoValidator pedidoValidator;
    CancelamentoValidator cancelamentoValidator;
    Calculator valorCalculator;
    PedidoDAO pedidoDAO;

    public VendaBusinessImpl() {
        vendaDAO = new VendaDAOImpl();
        pedidoValidator = new PedidoValidatorImpl();
        produtoDAO = new ProdutoDAOImpl();
        valorCalculator = new CalculatorImpl();
        cancelamentoValidator = new CancelamentoValidatorImpl();
        pedidoDAO = new PedidoDAOImpl();
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
            if (!pedidoValidator.validaQuantidadePedido(pedidoDTO)) {
                return false;
            }

            if (!pedidoValidator.validaQuantidadeProdutosPedidoEstoque(pedidoDTO, produtoBusca)) {
                return false;
            }

            if (vendaDAO.adicionarPedido(pedidoDTO)) {
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
            if (!pedidoValidator.validaDesconto(vendaDTO.getDesconto())) {
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
    public boolean motivoCancelamento(VendaDTO vendaDTO) {
        if (vendaDTO != null) {
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(vendaDTO.getId());
            for (PedidoDTO pedidoDTO : listaPedido) {
                ProdutoDTO produtoDTO = pedidoDTO.getProdutoDTO();
                produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() + pedidoDTO.getQuantidade());
                if (!produtoDAO.aumentarEstoque(produtoDTO)) {
                    return false;
                }
                if (!pedidoDAO.retirarPedido(pedidoDTO.getId())) {
                    return false;
                }
            }

            if (!cancelamentoValidator.validaMotivo(vendaDTO.getMotivoCancelamento())) {
                return false;
            }
            if (vendaDAO.atualizaMotivoCancelamento(vendaDTO)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean retirarPedido(int id) {
        PedidoDTO pedidoDTO = pedidoDAO.buscaPedido(id, "venda");
        ProdutoDTO produtoDTO = pedidoDTO.getProdutoDTO();
        produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() + pedidoDTO.getQuantidade());
        if (produtoDAO.aumentarEstoque(produtoDTO)) {
            pedidoDAO.retirarPedido(pedidoDTO.getId());
            return true;
        }
        return false;
    }
}
