package br.edu.fapi.dkrt.servlets.cadastro;

import br.edu.fapi.dkrt.business.cadastro.impl.ClienteBusinessImpl;
import br.edu.fapi.dkrt.business.cadastro.impl.ProdutoBusinessImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.dao.uf.UfDAO;
import br.edu.fapi.dkrt.dao.uf.impl.UfDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.uf.UfDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;
import br.edu.fapi.dkrt.servlets.mapper.impl.AlteraProdutoMapperImpl;
import br.edu.fapi.dkrt.servlets.mapper.impl.ClienteMapperImpl;
import br.edu.fapi.dkrt.servlets.mapper.impl.ProdutoMapperImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/cadastro")
public class CadastroServlet extends AbstractBaseHttpServlet {

    private BaseMapper<HttpServletRequest, ClienteDTO> clienteMapper = new ClienteMapperImpl();
    private BaseMapper<HttpServletRequest, ProdutoDTO> produtoMapper = new ProdutoMapperImpl();
    private BaseMapper<HttpServletRequest, ProdutoDTO> alteraProdutoMapper = new AlteraProdutoMapperImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("cliente".equalsIgnoreCase(tipo)) {
            ClienteBusinessImpl clienteBusiness = new ClienteBusinessImpl();

            ClienteDTO clienteDTO = clienteMapper.doMap(req);

            String condicao = clienteBusiness.cadastrarCliente(clienteDTO);

            if ("sucesso".equalsIgnoreCase(condicao)) {
                setSessionAttribute(req, "condicao", condicao);
                req.getRequestDispatcher("WEB-INF/cadastro/clienteCadastro.jsp").forward(req, resp);
            } else {
                setSessionAttribute(req, "condicao", condicao);
                setSessionAttribute(req, "tipoCadastro", "cliente");
                req.getRequestDispatcher("WEB-INF/cadastro/erroCadastro.jsp").forward(req, resp);
            }
        }

        if ("produto".equalsIgnoreCase(tipo)) {
            ProdutoBusinessImpl produtoBusiness = new ProdutoBusinessImpl();
            ProdutoDTO produtoDTO = new ProdutoDTO();

            try {
                produtoDTO = produtoMapper.doMap(req);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                setSessionAttribute(req, "condicao", "numInvalido");
                req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
            }

            String condicao = produtoBusiness.cadastrarProduto(produtoDTO, "cadastro");

            if ("sucesso".equalsIgnoreCase(condicao)) {
                setSessionAttribute(req, "condicao", condicao);
            } else if ("produtoExistente".equalsIgnoreCase(condicao)) {
                setSessionAttribute(req, "condicao", condicao);
                setSessionAttribute(req, "nomeProduto", produtoDTO.getNome());
            } else {
                setSessionAttribute(req, "condicao", condicao);
                setSessionAttribute(req, "tipoCadastro", "produto");
                req.getRequestDispatcher("WEB-INF/cadastro/erroCadastro.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }

        if ("alteraProduto".equalsIgnoreCase(tipo)) {
            ProdutoBusinessImpl produtoBusiness = new ProdutoBusinessImpl();
            ProdutoDTO produtoDTO = new ProdutoDTO();
            try {
                produtoDTO = alteraProdutoMapper.doMap(req);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                setSessionAttribute(req, "condicao", "numInvalido");
                req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
            }

            String condicao = produtoBusiness.cadastrarProduto(produtoDTO, "alteraProduto");

            if ("sucesso".equalsIgnoreCase(condicao)) {
                setSessionAttribute(req, "condicao", condicao);
                setSessionAttribute(req, "alteraForm", "nao");
                setSessionAttribute(req, "produtoBusca", null);
            } else {
                setSessionAttribute(req, "condicao", condicao);
                setSessionAttribute(req, "tipoCadastro", "produto");
                req.getRequestDispatcher("WEB-INF/cadastro/erroCadastro.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("cliente".equalsIgnoreCase(tipo)) {
            UfDAO ufDAO = new UfDAOImpl();
            List<UfDTO> listaUfs = ufDAO.buscarListaUfs();
            setSessionAttribute(req, "condicao", "");
            setSessionAttribute(req, "listaUfs", listaUfs);
            req.getRequestDispatcher("WEB-INF/cadastro/clienteCadastro.jsp").forward(req, resp);
        }

        if ("produto".equalsIgnoreCase(tipo)) {
            setSessionAttribute(req, "condicao", "");
            setSessionAttribute(req, "alteraForm", "nao");
            setSessionAttribute(req, "produtoBusca", null);
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }

        if ("alteraProduto".equalsIgnoreCase(tipo)) {
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            String nomeProduto = req.getParameter("nomeProduto");
            ProdutoDTO produtoBusca = produtoDAO.buscarProdutoPorNome(nomeProduto);
            setSessionAttribute(req, "produtoBusca", produtoBusca);
            setSessionAttribute(req, "condicao", "");
            setSessionAttribute(req, "alteraForm", "sim");
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }
    }

    public void setClienteMapper(BaseMapper<HttpServletRequest, ClienteDTO> clienteMapper) {
        this.clienteMapper = clienteMapper;
    }

    public void setProdutoMapper(BaseMapper<HttpServletRequest, ProdutoDTO> produtoMapper) {
        this.produtoMapper = produtoMapper;
    }

    public void setAlteraProdutoMapper(BaseMapper<HttpServletRequest, ProdutoDTO> alteraProdutoMapper) {
        this.alteraProdutoMapper = alteraProdutoMapper;
    }


}
