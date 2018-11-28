package br.edu.fapi.dkrt.servlets.pesquisa;

import br.edu.fapi.dkrt.business.cadastro.ClienteBusiness;
import br.edu.fapi.dkrt.business.cadastro.impl.ClienteBusinessImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
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
            req.getRequestDispatcher("WEB-INF/pesquisa/pesquisaCliente.jsp").forward(req, resp);
        }

        if ("produto".equalsIgnoreCase(tipo)) {

        }

        if ("pesquisaCliente".equalsIgnoreCase(tipo)) {
            String check = req.getParameter("checkTudo");

            if ("checkNome".equalsIgnoreCase(check)) {
                String palavra = req.getParameter("nomePesquisa");
                ClienteBusiness clienteBusiness = new ClienteBusinessImpl();
                List<ClienteDTO> listaClientesLike = clienteBusiness.pesquisarClienteLikeNome(palavra);
                setSessionAttribute(req, "listaClientesLike", listaClientesLike);
            }
            setSessionAttribute(req, "mostraTable", "sim");
            req.getRequestDispatcher("WEB-INF/pesquisa/pesquisaCliente.jsp").forward(req, resp);
        }

        if ("pesquisaProduto".equalsIgnoreCase(tipo)) {
            String parametro = req.getParameter("parametro");
        }
    }
}
