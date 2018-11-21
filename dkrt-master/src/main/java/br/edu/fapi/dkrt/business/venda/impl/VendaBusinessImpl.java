package br.edu.fapi.dkrt.business.venda.impl;

import br.edu.fapi.dkrt.business.venda.VendaBusiness;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.dao.venda.impl.VendaDAOImpl;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.validators.venda.VendaValidator;
import br.edu.fapi.dkrt.validators.venda.impl.VendaValidatorImpl;

public class VendaBusinessImpl implements VendaBusiness {
    VendaDAO vendaDAO;
    VendaValidator vendaValidator;
    ProdutoDAO produtoDAO;

    public VendaBusinessImpl() {
        vendaDAO = new VendaDAOImpl();
        vendaValidator = new VendaValidatorImpl();
        produtoDAO = new ProdutoDAOImpl();
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

            if (vendaDAO.finalizarVenda(vendaDTO)) {
                return true;
            }
        }
        return false;
    }
}
