package br.edu.fapi.dkrt.servlets.impressao;

import br.edu.fapi.dkrt.business.cadastro.ClienteBusiness;
import br.edu.fapi.dkrt.business.cadastro.ProdutoBusiness;
import br.edu.fapi.dkrt.business.cadastro.impl.ClienteBusinessImpl;
import br.edu.fapi.dkrt.business.cadastro.impl.ProdutoBusinessImpl;
import br.edu.fapi.dkrt.business.impressao.ImpressaoBusiness;
import br.edu.fapi.dkrt.business.impressao.impl.ImpressaoBusinessImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/impressao")
public class ImpressaoServlet extends AbstractBaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("clienteFicha".equalsIgnoreCase(tipo)) {
            String idCliente = req.getParameter("id");
            ImpressaoBusiness impressaoBusiness = new ImpressaoBusinessImpl();
            ClienteBusiness clienteBusiness = new ClienteBusinessImpl();
            ClienteDTO clienteDTO = clienteBusiness.buscarClienteId(Integer.parseInt(idCliente));
            if (impressaoBusiness.gerarPdfFichaCliente(clienteDTO)) {
                setSessionAttribute(req, "pdfSucesso", "sim");
                req.getRequestDispatcher("controller?acao=pesquisa&tipo=cliente").forward(req, resp);
            }
        }

        if ("produtoFicha".equalsIgnoreCase(tipo)){
            String idProduto = req.getParameter("id");
            ImpressaoBusiness impressaoBusiness = new ImpressaoBusinessImpl();
            ProdutoBusiness produtoBusiness = new ProdutoBusinessImpl();
            ProdutoDTO produtoDTO = produtoBusiness.buscarProdutoId(Integer.parseInt(idProduto));
            if (impressaoBusiness.gerarPdfFichaProduto(produtoDTO)){
                setSessionAttribute(req, "pdfSucesso", "sim");
                req.getRequestDispatcher("controller?acao=pesquisa&tipo=produto").forward(req, resp);
            }
        }

    }
}
