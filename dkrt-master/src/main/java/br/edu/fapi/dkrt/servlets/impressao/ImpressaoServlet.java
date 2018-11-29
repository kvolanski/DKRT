package br.edu.fapi.dkrt.servlets.impressao;

import br.edu.fapi.dkrt.business.impressao.ImpressaoBusiness;
import br.edu.fapi.dkrt.business.impressao.impl.ImpressaoBusinessImpl;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/impressao")
public class ImpressaoServlet extends AbstractBaseHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("clienteFicha".equalsIgnoreCase(tipo)){
            ImpressaoBusiness impressaoBusiness = new ImpressaoBusinessImpl();
            ClienteDTO clienteDTO = new ClienteDTO();
            if (impressaoBusiness.gerarPdfFichaCliente(clienteDTO)){
                req.getRequestDispatcher("controller?acao=pesquisa&tipo=cliente").forward(req, resp);
            }
        }

    }
}
