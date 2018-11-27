package br.edu.fapi.dkrt.servlets.Venda;

import br.edu.fapi.dkrt.business.calculator.Calculator;
import br.edu.fapi.dkrt.business.calculator.impl.CalculatorImpl;
import br.edu.fapi.dkrt.business.venda.VendaBusiness;
import br.edu.fapi.dkrt.business.venda.impl.VendaBusinessImpl;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.dao.venda.impl.VendaDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;
import br.edu.fapi.dkrt.servlets.mapper.impl.PedidoMapperImpl;
import br.edu.fapi.dkrt.servlets.mapper.impl.VendaMapperImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/venda")
public class VendaServlet extends AbstractBaseHttpServlet {

    private BaseMapper<HttpServletRequest, VendaDTO> vendaMapper = new VendaMapperImpl();
    private BaseMapper<HttpServletRequest, PedidoDTO> pedidoMapper = new PedidoMapperImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("buscaCliente".equalsIgnoreCase(tipo)) {
            String idCliente = req.getParameter("id");
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(idCliente));
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVenda2.jsp").forward(req, resp);
        }

        if ("adicionarPedido".equalsIgnoreCase(tipo)) {
            VendaDAO vendaDAO = new VendaDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            PedidoDTO pedidoDTO = pedidoMapper.doMap(req);
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            if (!vendaBusiness.adicionarPedido(pedidoDTO, (ProdutoDTO) req.getSession().getAttribute("produtoBusca"))) {
                setSessionAttribute(req, "erroQuantidade", "quantidadeInvalida");
            }
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(pedidoDTO.getVendaDTO().getId());
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getSession().removeAttribute("produtoBusca");
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("finalizarVenda".equalsIgnoreCase(tipo)) {
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            VendaDTO vendaDTO = vendaMapper.doMap(req);
            ClienteDTO clienteDTO = (ClienteDTO) req.getSession().getAttribute("clienteBusca");
            vendaDTO.setClienteDTO(clienteDTO);
            Calculator valorCalculator = new CalculatorImpl();
            List<PedidoDTO> listaPedido = (List<PedidoDTO>) req.getSession().getAttribute("listaPedido");
            vendaDTO.setValorTotal(valorCalculator.calcularValorTotal(listaPedido));
            if (vendaBusiness.finalizarVenda(vendaDTO)) {
                ClienteDAO clienteDAO = new ClienteDAOImpl();
                List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
                setSessionAttribute(req, "listaClientes", listaClientes);
                ClienteDTO clienteBusca = new ClienteDTO();
                clienteBusca.setId(0);
                req.getSession().removeAttribute("idVenda");
                req.getSession().removeAttribute("clienteBusca");
                req.getSession().removeAttribute("listaPedido");
                setSessionAttribute(req, "clienteBusca", clienteBusca);
                setSessionAttribute(req, "mostraCliente", "sim");
                req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("WEB-INF/vendaOrcamento/finalizacaoVenda.jsp").forward(req, resp);
            }
        }

        if ("cancelamentoVenda".equalsIgnoreCase(tipo)) {
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            int id = (int) req.getSession().getAttribute("idVenda");
            String motivo = req.getParameter("motivoCancelamento");
            VendaDTO vendaDTO = new VendaDTO();
            vendaDTO.setId(id);
            vendaDTO.setMotivoCancelamento(motivo);
            if (vendaBusiness.motivoCancelamento(vendaDTO)) {
                req.getRequestDispatcher("venda?tipo=listarVendas").forward(req, resp);
            } else {
                req.getRequestDispatcher("WEB-INF/vendaOrcamento/motivoCancelamento.jsp").forward(req, resp);
            }
        }

        if ("listarVendas".equalsIgnoreCase(tipo)) {
            VendaDAO vendaDAO = new VendaDAOImpl();
            List<VendaDTO> listaVendas = vendaDAO.listarVendas();
            setSessionAttribute(req, "listaVendas", listaVendas);
            setSessionAttribute(req, "tipoStatus", "normal");
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/listarVendas.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("efetuar".equalsIgnoreCase(tipo)) {
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            ClienteDTO clienteBusca = new ClienteDTO();
            clienteBusca.setId(0);
            setSessionAttribute(req, "listaClientes", listaClientes);
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            setSessionAttribute(req, "mostraCliente", "sim");
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("buscaProduto".equalsIgnoreCase(tipo)) {
            String idProduto = req.getParameter("idProduto");
            VendaDAO vendaDAO = new VendaDAOImpl();
            int id = (int) req.getSession().getAttribute("idVenda");
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            ProdutoDTO produtoBusca = produtoDAO.buscaProdutoPorId(Integer.parseInt(idProduto));
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(id);
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getSession().setAttribute("produtoBusca", produtoBusca);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("abrirVenda".equalsIgnoreCase(tipo)) {
            String idCliente = req.getParameter("id");
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            VendaDAO vendaDAO = new VendaDAOImpl();
            VendaDTO vendaDTO = new VendaDTO();
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(idCliente));
            vendaDTO.setStatus("Incompleta");
            vendaDTO.setClienteDTO(clienteBusca);
            vendaDTO.setMotivoCancelamento("A venda está incompleta pois foi encerrada de forma inesperada");
            vendaDTO.setId(vendaBusiness.abrirVenda(vendaDTO));
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(vendaDTO.getId());
            setSessionAttribute(req, "listaPedido", listaPedido);
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaClientes", listaClientes);
            setSessionAttribute(req, "mostraCliente", "nao");
            req.getSession().setAttribute("clienteBusca", clienteBusca);
            req.getSession().setAttribute("idVenda", vendaDTO.getId());
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("finalizarVenda".equalsIgnoreCase(tipo)) {
            Calculator calculator = new CalculatorImpl();
            int id = (int) req.getSession().getAttribute("idVenda");
            VendaDAO vendaDAO = new VendaDAOImpl();
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(id);
            float valorTotal = calculator.calcularValorTotal(listaPedido);
            req.getSession().setAttribute("listaPedido", listaPedido);
            setSessionAttribute(req, "valorTotal", valorTotal);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/finalizacaoVenda.jsp").forward(req, resp);
        }

        if ("listarVendas".equalsIgnoreCase(tipo)) {
            VendaDAO vendaDAO = new VendaDAOImpl();
            List<VendaDTO> listaVendas = vendaDAO.listarVendas();
            setSessionAttribute(req, "tipoStatus", "normal");
            setSessionAttribute(req, "listaVendas", listaVendas);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/listarVendas.jsp").forward(req, resp);
        }

        if ("buscaVenda".equalsIgnoreCase(tipo)) {
            String id = req.getParameter("id");
            VendaDAO vendaDAO = new VendaDAOImpl();
            VendaDTO vendaBusca = vendaDAO.buscaVenda(Integer.parseInt(id));
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(Integer.parseInt(id));
            setSessionAttribute(req, "vendaBusca", vendaBusca);
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/displayVenda.jsp").forward(req, resp);
        }

        if ("vendaEmAberto".equalsIgnoreCase(tipo)) {
            VendaDAO vendaDAO = new VendaDAOImpl();
            int id = (int) req.getSession().getAttribute("idVenda");
            VendaDTO vendaDTO = new VendaDTO();
            vendaDTO.setId(id);
            vendaDTO.setStatus("Em aberto");
            vendaDAO.atualizaStatus(vendaDTO);
            req.getSession().removeAttribute("idVenda");
            req.getRequestDispatcher("venda?tipo=efetuar").forward(req, resp);
        }

        if ("cancelarVenda".equalsIgnoreCase(tipo)) {
            VendaDAO vendaDAO = new VendaDAOImpl();
            int id = (int) req.getSession().getAttribute("idVenda");
            VendaDTO vendaDTO = new VendaDTO();
            vendaDTO.setId(id);
            vendaDTO.setStatus("Cancelada");
            vendaDAO.atualizaStatus(vendaDTO);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/motivoCancelamento.jsp").forward(req, resp);
        }

        if ("adicionarProdutosEmAberto".equalsIgnoreCase(tipo)) {
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            VendaDAO vendaDAO = new VendaDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            int idVenda = (int) req.getSession().getAttribute("idVenda");
            String idCliente = req.getParameter("idCliente");
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(idCliente));
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(idVenda);
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            setSessionAttribute(req, "listaPedido", listaPedido);
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            req.getSession().setAttribute("clienteBusca", clienteBusca);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
        }

        if ("tirarProdutoLista".equalsIgnoreCase(tipo)) {
            VendaDAO vendaDAO = new VendaDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            String idPedido = req.getParameter("id");
            int idVenda = (int) req.getSession().getAttribute("idVenda");
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            if (vendaBusiness.retirarPedido(Integer.parseInt(idPedido))) {
                List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(idVenda);
                setSessionAttribute(req, "listaPedido", listaPedido);
                req.getRequestDispatcher("WEB-INF/vendaOrcamento/efetuarVendaOrcamento.jsp").forward(req, resp);
            }
        }

        if ("listarVendasCanceladas".equalsIgnoreCase(tipo)) {
            VendaDAO vendaDAO = new VendaDAOImpl();
            List<VendaDTO> listaVendas = vendaDAO.listarVendas();
            setSessionAttribute(req, "tipoStatus", "cancelada");
            setSessionAttribute(req, "listaVendas", listaVendas);
            req.getRequestDispatcher("WEB-INF/vendaOrcamento/listarVendas.jsp").forward(req, resp);
        }
    }

    public void setVendaMapper(BaseMapper<HttpServletRequest, VendaDTO> vendaMapper) {
        this.vendaMapper = vendaMapper;
    }

    public void setPedidoMapper(BaseMapper<HttpServletRequest, PedidoDTO> pedidoMapper) {
        this.pedidoMapper = pedidoMapper;
    }

}
