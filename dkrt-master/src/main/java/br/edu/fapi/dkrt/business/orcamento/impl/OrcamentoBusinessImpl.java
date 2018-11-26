package br.edu.fapi.dkrt.business.orcamento.impl;

import br.edu.fapi.dkrt.business.orcamento.OrcamentoBusiness;
import br.edu.fapi.dkrt.dao.orcamento.OrcamentoDAO;
import br.edu.fapi.dkrt.dao.orcamento.impl.OrcamentoDAOImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.validators.venda.PedidoValidator;
import br.edu.fapi.dkrt.validators.venda.impl.PedidoValidatorImpl;

public class OrcamentoBusinessImpl implements OrcamentoBusiness {
    OrcamentoDAO orcamentoDAO;
    PedidoValidator pedidoValidator;
    ProdutoDAO produtoDAO;

    public OrcamentoBusinessImpl(){
        orcamentoDAO = new OrcamentoDAOImpl();
        pedidoValidator = new PedidoValidatorImpl();
        produtoDAO = new ProdutoDAOImpl();
    }

    @Override
    public int abrirOrcamento(OrcamentoDTO orcamentoDTO) {
        int id = 0;
        if (orcamentoDTO != null){
            id = orcamentoDAO.abrirOrcamento(orcamentoDTO);
            return id;
        }
        return id;
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

            if (orcamentoDAO.adicionarPedido(pedidoDTO)) {
                return true;
            }
        }
        return false;
    }
}
