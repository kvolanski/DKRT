package br.edu.fapi.dkrt.servlets.pesquisa;

import br.edu.fapi.dkrt.business.cadastro.ClienteBusiness;
import br.edu.fapi.dkrt.business.cadastro.ProdutoBusiness;
import br.edu.fapi.dkrt.business.cadastro.impl.ClienteBusinessImpl;
import br.edu.fapi.dkrt.business.cadastro.impl.ProdutoBusinessImpl;
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

@WebServlet(urlPatterns = "/pesquisa")
public class PesquisaServlet extends AbstractBaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("cliente".equalsIgnoreCase(tipo)) {
            setSessionAttribute(req, "mostraTable", "nao");
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            setSessionAttribute(req, "listaClientes", listaClientes);
            req.getRequestDispatcher("WEB-INF/pesquisa/pesquisaCliente.jsp").forward(req, resp);
        }

        if ("produto".equalsIgnoreCase(tipo)) {
            setSessionAttribute(req, "mostraTable", "nao");
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            req.getRequestDispatcher("WEB-INF/pesquisa/pesquisaProduto.jsp").forward(req, resp);
        }

        if ("pesquisaCliente".equalsIgnoreCase(tipo)) {
            String check = req.getParameter("checkTudo");

            if ("checkNome".equalsIgnoreCase(check)) {
                String palavra = req.getParameter("nomePesquisa");
                ClienteBusiness clienteBusiness = new ClienteBusinessImpl();
                List<ClienteDTO> listaClientesLike = clienteBusiness.pesquisarClienteLikeNome(palavra);
                setSessionAttribute(req, "listaClientesLike", listaClientesLike);
                setSessionAttribute(req, "mostraTable", "sim");
                setSessionAttribute(req, "mostrarDetalhe", "nao");
            }

            if ("checkId".equalsIgnoreCase(check)) {
                String id = req.getParameter("idPesquisa");
                ClienteBusiness clienteBusiness = new ClienteBusinessImpl();
                ClienteDTO clienteDTO = clienteBusiness.buscarClienteId(Integer.parseInt(id));
                setSessionAttribute(req, "clienteBusca", clienteDTO);
                setSessionAttribute(req, "mostraTable", "nao");
                setSessionAttribute(req, "mostrarDetalhe", "sim");
            }

            if ("checkNumCompras".equalsIgnoreCase(check)) {
                ClienteBusiness clienteBusiness = new ClienteBusinessImpl();
                List<ClienteDTO> listaClientesLike = clienteBusiness.listarClienteNumCompras();
                setSessionAttribute(req, "listaClientesLike", listaClientesLike);
                setSessionAttribute(req, "mostraTable", "sim");
                setSessionAttribute(req, "mostrarDetalhe", "nao");
            }

            ClienteDAO clienteDAO = new ClienteDAOImpl();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            setSessionAttribute(req, "listaClientes", listaClientes);
            req.getRequestDispatcher("WEB-INF/pesquisa/pesquisaCliente.jsp").forward(req, resp);
        }

        if ("pesquisaProduto".equalsIgnoreCase(tipo)) {
            String check = req.getParameter("checkTudo");

            if ("checkNome".equalsIgnoreCase(check)) {
                String palavra = req.getParameter("nomePesquisa");
                ProdutoBusiness produtoBusiness = new ProdutoBusinessImpl();
                List<ProdutoDTO> listaProdutosLike = produtoBusiness.pesquisarProdutoLikeNome(palavra);
                setSessionAttribute(req, "listaProdutosLike", listaProdutosLike);
                setSessionAttribute(req, "mostraTable", "sim");
                setSessionAttribute(req, "mostrarDetalhe", "nao");
            }

            if ("checkId".equalsIgnoreCase(check)) {
                String id = req.getParameter("idPesquisa");
                ProdutoBusiness produtoBusiness = new ProdutoBusinessImpl();
                ProdutoDTO produtoDTO = produtoBusiness.buscarProdutoId(Integer.parseInt(id));
                setSessionAttribute(req, "produtoBusca", produtoDTO);
                setSessionAttribute(req, "mostraTable", "nao");
                setSessionAttribute(req, "mostrarDetalhe", "sim");
            }

            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            req.getRequestDispatcher("WEB-INF/pesquisa/pesquisaProduto.jsp").forward(req, resp);
        }

        if ("listarClientes".equalsIgnoreCase(tipo)) {
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
            setSessionAttribute(req, "listaClientes", listaClientes);
            req.getRequestDispatcher("WEB-INF/clientes/listarClientes.jsp").forward(req, resp);
        }

        if ("excluirCliente".equalsIgnoreCase(tipo)){
            ClienteBusiness clienteBusiness = new ClienteBusinessImpl();
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            String idCliente = req.getParameter("id");
            if (clienteBusiness.excluirCliente(Integer.parseInt(idCliente))){
                List<ClienteDTO> listaClientes = clienteDAO.listarClientes();
                setSessionAttribute(req, "listaClientes", listaClientes);
                req.getRequestDispatcher("WEB-INF/clientes/listarClientes.jsp").forward(req, resp);
            }
        }
    }
}
