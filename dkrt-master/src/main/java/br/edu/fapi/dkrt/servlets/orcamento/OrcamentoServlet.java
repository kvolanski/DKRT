package br.edu.fapi.dkrt.servlets.orcamento;

import br.edu.fapi.dkrt.business.calculator.Calculator;
import br.edu.fapi.dkrt.business.calculator.impl.CalculatorImpl;
import br.edu.fapi.dkrt.business.orcamento.OrcamentoBusiness;
import br.edu.fapi.dkrt.business.orcamento.impl.OrcamentoBusinessImpl;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.orcamento.OrcamentoDAO;
import br.edu.fapi.dkrt.dao.orcamento.impl.OrcamentoDAOImpl;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/orcamento")
public class OrcamentoServlet extends AbstractBaseHttpServlet {

    BaseMapper<HttpServletRequest, OrcamentoDTO> orcamentoMapper = new OrcamentoMapperImpl();
    BaseMapper<HttpServletRequest, PedidoDTO> pedidoMapper = new PedidoMapperImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("adicionarPedidoOrcamento".equalsIgnoreCase(tipo)) {
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            PedidoDTO pedidoDTO = pedidoMapper.doMap(req);
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            if (!orcamentoBusiness.adicionarPedido(pedidoDTO, (ProdutoDTO) req.getSession().getAttribute("produtoBusca"))) {
                setSessionAttribute(req, "erroQuantidade", "quantidadeInvalida");
            }
            List<PedidoDTO> listaPedido = orcamentoDAO.listarPedidosOrcamento(pedidoDTO.getOrcamentoDTO().getId());
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getSession().removeAttribute("produtoBusca");
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("finalizarOrcamento".equalsIgnoreCase(tipo)) {
            OrcamentoDTO orcamentoDTO = orcamentoMapper.doMap(req);
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            if (orcamentoBusiness.finalizarOrcamento(orcamentoDTO)) {
                List<OrcamentoDTO> listaOrcamentos = orcamentoDAO.listarOrcamentos();
                setSessionAttribute(req, "listaOrcamentos", listaOrcamentos);
                req.getSession().removeAttribute("orcamentoBusca");
                req.getRequestDispatcher("WEB-INF/orcamento/listarOrcamentos.jsp").forward(req, resp);
            } else {
                OrcamentoDTO orcamentoBusca = (OrcamentoDTO) req.getSession().getAttribute("orcamentoBusca");
                List<PedidoDTO> listaPedido = orcamentoDAO.listarPedidosOrcamento(orcamentoBusca.getId());
                setSessionAttribute(req, "listaPedido", listaPedido);
                req.getRequestDispatcher("WEB-INF/vendaOrcamento/finalizacaoOrcamento.jsp").forward(req, resp);
            }
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
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("comecoOrcamento".equalsIgnoreCase(tipo)) {
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            String id = req.getParameter("idCliente");
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(id));
            OrcamentoDTO orcamentoDTO = new OrcamentoDTO();
            orcamentoDTO.setClienteDTO(clienteBusca);
            orcamentoDTO.setStatus("Incompleto");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = dateFormat.format(new Date());
            try {
                orcamentoDTO.setDataGeracao(dateFormat.parse(data));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            orcamentoDTO.setId(orcamentoBusiness.abrirOrcamento(orcamentoDTO));
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<PedidoDTO> listaPedido = orcamentoDAO.listarPedidosOrcamento(orcamentoDTO.getId());
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getSession().setAttribute("orcamentoBusca", orcamentoDTO);
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "mostraCliente", "nao");
            setSessionAttribute(req, "tipoDeAcao", "orcamento");
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("buscarProduto".equalsIgnoreCase(tipo)) {
            String idProduto = req.getParameter("idProduto");
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            OrcamentoDTO orcamentoBusca = (OrcamentoDTO) req.getSession().getAttribute("orcamentoBusca");
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            ProdutoDTO produtoBusca = produtoDAO.buscaProdutoPorId(Integer.parseInt(idProduto));
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<PedidoDTO> listaPedido = orcamentoDAO.listarPedidosOrcamento(orcamentoBusca.getId());
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getSession().setAttribute("produtoBusca", produtoBusca);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("finalizarOrcamento".equalsIgnoreCase(tipo)) {
            Calculator calculator = new CalculatorImpl();
            OrcamentoDTO orcamentoBusca = (OrcamentoDTO) req.getSession().getAttribute("orcamentoBusca");
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            List<PedidoDTO> listaPedido = orcamentoDAO.listarPedidosOrcamento(orcamentoBusca.getId());
            float valorTotal = calculator.calcularValorTotal(listaPedido);
            orcamentoBusca.setValorTotal(valorTotal);
            req.getSession().setAttribute("orcamentoBusca", orcamentoBusca);
            req.getSession().setAttribute("listaPedido", listaPedido);
            setSessionAttribute(req, "valorTotal", valorTotal);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/finalizacaoOrcamento.jsp").forward(req, resp);
        }

        if ("tirarProdutoLista".equalsIgnoreCase(tipo)) {
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            OrcamentoDTO orcamentoBusca = (OrcamentoDTO) req.getSession().getAttribute("orcamentoBusca");
            String idPedido = req.getParameter("id");
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            if (orcamentoBusiness.retirarPedido(Integer.parseInt(idPedido))) {
                List<PedidoDTO> listaPedido = orcamentoDAO.listarPedidosOrcamento(orcamentoBusca.getId());
                setSessionAttribute(req, "listaPedido", listaPedido);
                req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
            }
        }

        if ("listarOrcamentos".equalsIgnoreCase(tipo)) {
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            List<OrcamentoDTO> listaOrcamentos = orcamentoBusiness.confereListaOrcamentos();
            setSessionAttribute(req, "listaOrcamentos", listaOrcamentos);
            req.getRequestDispatcher("WEB-INF/orcamento/listarOrcamentos.jsp").forward(req, resp);
        }

        if ("excluirOrcamento".equalsIgnoreCase(tipo)){
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            String idOrcamento = req.getParameter("id");
            if (orcamentoBusiness.excluirOrcamento(Integer.parseInt(idOrcamento))){
                List<OrcamentoDTO> listaOrcamentos = orcamentoDAO.listarOrcamentos();
                setSessionAttribute(req, "listaOrcamentos", listaOrcamentos);
                req.getRequestDispatcher("WEB-INF/orcamento/listarOrcamentos.jsp").forward(req, resp);
            }
        }

        if ("comecarVendaOrcamento".equalsIgnoreCase(tipo)){
            OrcamentoBusiness orcamentoBusiness = new OrcamentoBusinessImpl();
            OrcamentoDAO orcamentoDAO = new OrcamentoDAOImpl();
            String idOrcamento = req.getParameter("id");
            OrcamentoDTO orcamentoDTO = orcamentoDAO.buscarOrcamento(Integer.parseInt(idOrcamento));
            orcamentoDTO.setStatus("Vendido");
            int idVenda = orcamentoBusiness.adicionaIdVendaOrcamento(orcamentoDTO);
            req.getSession().setAttribute("idVenda", idVenda);
            req.getRequestDispatcher("venda?tipo=finalizarVenda").forward(req, resp);
        }
    }

    public void setOrcamentoMapper(BaseMapper<HttpServletRequest, OrcamentoDTO> orcamentoMapper) {
        this.orcamentoMapper = orcamentoMapper;
    }

    public void setPedidoMapper(BaseMapper<HttpServletRequest, PedidoDTO> pedidoMapper) {
        this.pedidoMapper = pedidoMapper;
    }
}
