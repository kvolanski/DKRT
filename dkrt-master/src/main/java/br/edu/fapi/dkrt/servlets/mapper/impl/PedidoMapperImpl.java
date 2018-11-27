package br.edu.fapi.dkrt.servlets.mapper.impl;

import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;

import javax.servlet.http.HttpServletRequest;

public class PedidoMapperImpl implements BaseMapper<HttpServletRequest, PedidoDTO> {

    @Override
    public PedidoDTO doMap(HttpServletRequest req) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        String tipoAlteracao = req.getParameter("tipoAlteracao");
        if ("orcamento".equalsIgnoreCase(tipoAlteracao)) {
            OrcamentoDTO orcamentoDTO = (OrcamentoDTO) req.getSession().getAttribute("orcamentoBusca");
            pedidoDTO.setOrcamentoDTO(orcamentoDTO);
        } else {
            int idVenda = (int) req.getSession().getAttribute("idVenda");
            VendaDTO vendaDTO = new VendaDTO();
            vendaDTO.setId(idVenda);
            pedidoDTO.setVendaDTO(vendaDTO);
        }
        String idProduto = req.getParameter("idProduto");
        String quantidade = req.getParameter("quantidadeProduto");
        String valorUnit = req.getParameter("valorUnitProduto");
        if (valorUnit.contains(",")) {
            valorUnit.replaceAll(",", ".");
        }
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(Integer.parseInt(idProduto));
        pedidoDTO.setProdutoDTO(produtoDTO);
        pedidoDTO.setQuantidade(Integer.parseInt(quantidade));
        pedidoDTO.setValorUnitario(Float.parseFloat(valorUnit));
        float valorTotal = Float.parseFloat(valorUnit) * Integer.parseInt(quantidade);
        pedidoDTO.setValorTotal(valorTotal);
        return pedidoDTO;
    }
}
