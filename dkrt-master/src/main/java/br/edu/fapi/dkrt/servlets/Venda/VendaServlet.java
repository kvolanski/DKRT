package br.edu.fapi.dkrt.servlets.Venda;

import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = "/venda")
public class VendaServlet extends AbstractBaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("buscaCliente".equalsIgnoreCase(tipo)) {
            String idCliente = req.getParameter("id");
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(idCliente));
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaClientes", listaClientes);
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
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
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }

        if ("buscaProduto".equalsIgnoreCase(tipo)){
            String idCliente = req.getParameter("idCliente");
            String idProduto = req.getParameter("idProduto");
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(idCliente));
            ProdutoDTO produtoBusca = produtoDAO.buscaProdutoPorId(Integer.parseInt(idProduto));
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            setSessionAttribute(req, "produtoBusca", produtoBusca);
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }

        if ("buscaCliente".equalsIgnoreCase(tipo)) {
            String idCliente = req.getParameter("id");
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            ClienteDTO clienteBusca = clienteDAO.buscarCliente(Integer.parseInt(idCliente));
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            setSessionAttribute(req, "listaClientes", listaClientes);
            setSessionAttribute(req, "clienteBusca", clienteBusca);
            req.getRequestDispatcher("WEB-INF/venda/efetuarVenda.jsp").forward(req, resp);
        }
    }
}
