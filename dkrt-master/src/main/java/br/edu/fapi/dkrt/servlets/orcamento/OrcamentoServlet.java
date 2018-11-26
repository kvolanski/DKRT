package br.edu.fapi.dkrt.servlets.orcamento;

import br.edu.fapi.dkrt.business.orcamento.OrcamentoBusiness;
import br.edu.fapi.dkrt.business.orcamento.impl.OrcamentoBusinessImpl;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;
import br.edu.fapi.dkrt.servlets.mapper.impl.OrcamentoMapperImpl;
import br.edu.fapi.dkrt.servlets.mapper.impl.PedidoMapperImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/orcamento")
public class OrcamentoServlet extends AbstractBaseHttpServlet {

    BaseMapper<HttpServletRequest, OrcamentoDTO> orcamentoMapper = new OrcamentoMapperImpl();
    BaseMapper<HttpServletRequest, PedidoDTO> pedidoMapper = new PedidoMapperImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("adicionarPedidoOrcamento".equalsIgnoreCase(tipo)){
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("iniciarOrcamento".equalsIgnoreCase(tipo)) {
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ClienteDTO clienteBusca = new ClienteDTO();
            clienteBusca.setId(0);
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            setSessionAttribute(req, "listaClientes", listaClientes);
            req.getSession().setAttribute("tipoDeAcao", "orcamento");
            setSessionAttribute(req, "mostraCliente", "sim");
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }

        if ("comecoOrcamento".equalsIgnoreCase(tipo)) {
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            String id = req.getParameter("idCliente");
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(id));
            OrcamentoDTO orcamentoDTO = new OrcamentoDTO();
            orcamentoDTO.setClienteDTO(clienteBusca);
            orcamentoDTO.setStatus("Incompleto");
            orcamentoDTO.setId(orcamentoBusiness.abrirOrcamento(orcamentoDTO));
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            req.getSession().setAttribute("orcamentoBusca", orcamentoDTO);
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "mostraCliente", "nao");
            setSessionAttribute(req, "tipoDeAcao", "orcamento");
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);

        }

        if ("finalizarOrcamento".equalsIgnoreCase(tipo)) {

        }
    }

    public void setOrcamentoMapper(BaseMapper<HttpServletRequest, OrcamentoDTO> orcamentoMapper) {
        this.orcamentoMapper = orcamentoMapper;
    }

    public void setPedidoMapper(BaseMapper<HttpServletRequest, PedidoDTO> pedidoMapper) {
        this.pedidoMapper = pedidoMapper;
    }
}
