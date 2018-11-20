package br.edu.fapi.dkrt.servlets.Venda;

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
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda2.jsp").forward(req, resp);
        }

        if ("adicionarPedido".equalsIgnoreCase(tipo)){
            BaseMapper pedidoMapper = new PedidoMapperImpl();
            PedidoDTO pedidoDTO = ((PedidoMapperImpl) pedidoMapper).doMap(req);
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            vendaBusiness.adicionarPedido(pedidoDTO);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            VendaDAO vendaDAO = new VendaDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(pedidoDTO.getVendaDTO().getId(), "venda");
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }

        if ("finalizarVenda".equalsIgnoreCase(tipo)){
            VendaDAO vendaDAO = new VendaDAOImpl();
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            VendaDTO vendaDTO = vendaMapper.doMap(req);
            ClienteDTO clienteDTO = (ClienteDTO) req.getSession().getAttribute("clienteBusca");
            vendaDTO.setClienteDTO(clienteDTO);
            if(vendaBusiness.finalizarVenda(vendaDTO)){
                ClienteDAO clienteDAO = new ClienteDAOImpl();
                List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
                setSessionAttribute(req, "listaClientes", listaClientes);
                ClienteDTO clienteBusca = new ClienteDTO();
                clienteBusca.setId(0);
                setSessionAttribute(req, "clienteBusca", clienteBusca);
                setSessionAttribute(req, "mostraCliente", "sim");
                req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
            }
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
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }

        if ("buscaProduto".equalsIgnoreCase(tipo)) {
            String idProduto = req.getParameter("idProduto");
            VendaDAO vendaDAO = new VendaDAOImpl();
            int id = (int) req.getSession().getAttribute("idVenda");
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            ProdutoDTO produtoBusca = produtoDAO.buscaProdutoPorId(Integer.parseInt(idProduto));
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "produtoBusca", produtoBusca);
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(id, "venda");
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }

        if ("abrirVenda".equalsIgnoreCase(tipo)) {
            String idCliente = req.getParameter("id");
            VendaBusiness vendaBusiness = new VendaBusinessImpl();
            VendaDTO vendaDTO = new VendaDTO();
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(idCliente));
            vendaDTO.setStatus("Incompleta");
            vendaDTO.setClienteDTO(clienteBusca);
            vendaDTO.setId(vendaBusiness.abrirVenda(vendaDTO));
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaClientes", listaClientes);
            setSessionAttribute(req, "mostraCliente", "nao");
            req.getSession().setAttribute("clienteBusca", clienteBusca);
            req.getSession().setAttribute("idVenda", vendaDTO.getId());
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }

        if ("finalizarVenda".equalsIgnoreCase(tipo)){
            int id = (int) req.getSession().getAttribute("idVenda");
            VendaDAO vendaDAO = new VendaDAOImpl();
            List<PedidoDTO> listaPedido = vendaDAO.listarPedidosVenda(id, "venda");
            setSessionAttribute(req, "listaPedido", listaPedido);
            req.getRequestDispatcher("WEB-INF/venda/finalizacaoVenda.jsp").forward(req, resp);
        }
    }

    public void setVendaMapper(BaseMapper<HttpServletRequest, VendaDTO> vendaMapper) {
        this.vendaMapper = vendaMapper;
    }
}
