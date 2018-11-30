package br.edu.fapi.dkrt.servlets.estoque;

import br.edu.fapi.dkrt.business.cadastro.ProdutoBusiness;
import br.edu.fapi.dkrt.business.cadastro.impl.ProdutoBusinessImpl;
import br.edu.fapi.dkrt.business.estoque.EstoqueBusiness;
import br.edu.fapi.dkrt.business.estoque.impl.EstoqueBusinessImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;
import br.edu.fapi.dkrt.servlets.mapper.impl.ProdutoMapperImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/estoque")
public class EstoqueServlet extends AbstractBaseHttpServlet {

    private BaseMapper<HttpServletRequest, ProdutoDTO> produtoMapper = new ProdutoMapperImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("editarProduto".equalsIgnoreCase(tipo)) {
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            EstoqueBusiness estoqueBusiness = new EstoqueBusinessImpl();
            String idProduto = req.getParameter("idProduto");
            ProdutoDTO produtoDTO = produtoMapper.doMap(req);
            produtoDTO.setId(Integer.parseInt(idProduto));
            if (estoqueBusiness.atualizaProduto(produtoDTO)){
                List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
                setSessionAttribute(req, "listaProdutos", listaProdutos);
                req.getRequestDispatcher("WEB-INF/estoque/listarEstoque.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("listarEstoque".equalsIgnoreCase(tipo)) {
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            setSessionAttribute(req, "listaProdutos", listaProdutos);
            req.getRequestDispatcher("WEB-INF/estoque/listarEstoque.jsp").forward(req, resp);
        }

        if ("alteraProduto".equalsIgnoreCase(tipo)) {
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            String idProduto = req.getParameter("idProduto");
            ProdutoDTO produtoBusca = produtoDAO.buscaProdutoPorId(Integer.parseInt(idProduto));
            setSessionAttribute(req, "produtoBusca", produtoBusca);
            req.getRequestDispatcher("WEB-INF/estoque/edicaoProduto.jsp").forward(req, resp);
        }

        if ("excluiProduto".equalsIgnoreCase(tipo)){
            ProdutoBusiness produtoBusiness = new ProdutoBusinessImpl();
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            String idProduto = req.getParameter("idProduto");
            if (produtoBusiness.excluirProduto(Integer.parseInt(idProduto))){
                List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
                setSessionAttribute(req, "listaProdutos", listaProdutos);
                req.getRequestDispatcher("WEB-INF/estoque/listarEstoque.jsp").forward(req, resp);
            }
        }
    }

    public void setProdutoMapper(BaseMapper<HttpServletRequest, ProdutoDTO> produtoMapper) {
        this.produtoMapper = produtoMapper;
    }
}
