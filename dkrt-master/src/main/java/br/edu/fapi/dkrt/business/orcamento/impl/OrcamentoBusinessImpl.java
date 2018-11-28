package br.edu.fapi.dkrt.business.orcamento.impl;

import br.edu.fapi.dkrt.business.orcamento.OrcamentoBusiness;
import br.edu.fapi.dkrt.dao.orcamento.OrcamentoDAO;
import br.edu.fapi.dkrt.dao.orcamento.impl.OrcamentoDAOImpl;
import br.edu.fapi.dkrt.dao.pedido.PedidoDAO;
import br.edu.fapi.dkrt.dao.pedido.impl.PedidoDAOImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.dao.venda.impl.VendaDAOImpl;
import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.validators.venda.PedidoValidator;
import br.edu.fapi.dkrt.validators.venda.impl.PedidoValidatorImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrcamentoBusinessImpl implements OrcamentoBusiness {
    OrcamentoDAO orcamentoDAO;
    PedidoValidator pedidoValidator;
    ProdutoDAO produtoDAO;
    PedidoDAO pedidoDAO;
    VendaDAO vendaDAO;

    public OrcamentoBusinessImpl() {
        orcamentoDAO = new OrcamentoDAOImpl();
        pedidoValidator = new PedidoValidatorImpl();
        produtoDAO = new ProdutoDAOImpl();
        pedidoDAO = new PedidoDAOImpl();
        vendaDAO = new VendaDAOImpl();
    }

    @Override
    public int abrirOrcamento(OrcamentoDTO orcamentoDTO) {
        int id = 0;
        if (orcamentoDTO != null) {
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

    @Override
    public boolean retirarPedido(int id) {
        if (id != 0) {
            pedidoDAO.retirarPedido(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean finalizarOrcamento(OrcamentoDTO orcamentoDTO) {
        if (orcamentoDTO != null) {
            if (orcamentoDTO.getDataExpiracao() != null){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String data = dateFormat.format(new Date());
                String dataOrcamento = dateFormat.format(orcamentoDTO.getDataExpiracao());
                Date date = new Date();
                try {
                    date = dateFormat.parse(data);
                    orcamentoDTO.setDataExpiracao(dateFormat.parse(dataOrcamento));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (orcamentoDTO.getDataExpiracao().before(date)){
                    return false;
                }
            }

            if (!orcamentoDAO.finalizarOrcamento(orcamentoDTO)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean excluirOrcamento(int id) {
        if (id != 0) {
            if (!pedidoDAO.retirarPedidoOrcamento(id)) {
                return false;
            }
            if (orcamentoDAO.excluirOrcamento(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int adicionaIdVendaOrcamento(OrcamentoDTO orcamentoDTO) {
        if (orcamentoDTO != null) {
            VendaDTO vendaDTO = new VendaDTO();
            vendaDTO.setStatus("Incompleta");
            vendaDTO.setMotivoCancelamento("A venda está incompleta pois foi encerrada de forma inesperada");
            vendaDTO.setClienteDTO(orcamentoDTO.getClienteDTO());
            vendaDTO.setDesconto(orcamentoDTO.getDesconto());
            vendaDTO.setVendaOrcamento("Sim");
            int idVenda = vendaDAO.abrirVenda(vendaDTO);
            if (orcamentoDAO.adicionaIdVendaOrcamento(orcamentoDTO.getId(), idVenda)) {
                orcamentoDAO.atualizarStatusOrcamento(orcamentoDTO);
                List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(idVenda);
                for (PedidoDTO pedido : listaPedido) {
                    ProdutoDTO produtoDTO = produtoDAO.buscaProdutoPorId(pedido.getProdutoDTO().getId());
                    produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() - pedido.getQuantidade());
                    produtoDAO.diminuirEstoque(produtoDTO);
                }
            }
            return idVenda;
        }
        return 0;
    }

    @Override
    public List<OrcamentoDTO> confereListaOrcamentos() {
        List<OrcamentoDTO> listaOrcamentos = orcamentoDAO.listarOrcamentos();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String data = dateFormat.format(new Date());
        Date date = new Date();
        try {
            date = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (OrcamentoDTO orcamento : listaOrcamentos) {
            String dataOrcamento = dateFormat.format(orcamento.getDataExpiracao());
            try {
                orcamento.setDataExpiracao(dateFormat.parse(dataOrcamento));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (orcamento.getDataExpiracao().before(date)) {
                orcamento.setStatus("Expirado");
                orcamentoDAO.atualizarStatusOrcamento(orcamento);
            }
        }
        listaOrcamentos = orcamentoDAO.listarOrcamentos();
        return listaOrcamentos;
    }
}
