package br.edu.fapi.dkrt.servlets.controller;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if ("login".equalsIgnoreCase(acao)) {
            String usuario = req.getParameter("usuario");
            String senha = req.getParameter("senha");

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setEmail(usuario);
            usuarioDTO.setSenha(senha);
            req.getSession().setAttribute("usuario", usuarioDTO);
            req.getRequestDispatcher("login").forward(req, resp);
        }

        if ("excluir".equalsIgnoreCase(acao)) {
            req.getRequestDispatcher("/excluir").forward(req, resp);
        }

        if ("cadastro".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("cliente".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=cliente").forward(req, resp);
            } else if ("produto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=produto").forward(req, resp);
            } else if ("alteraProduto".equalsIgnoreCase(tipo)) {
                String idProduto = req.getParameter("idProduto");
                req.getRequestDispatcher("cadastro?tipo=alteraProduto&idProduto=" + idProduto).forward(req, resp);
            }
        }

        if ("venda".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        System.out.println("EU ESTIVE AQUI");

        if ("cadastro".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("produto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=produto").forward(req, resp);
            } else if ("cliente".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=cliente").forward(req, resp);
            } else if ("alteraProduto".equalsIgnoreCase(tipo)) {
                String nomeProduto = req.getParameter("nomeProduto");
                req.getRequestDispatcher("cadastro?tipo=alteraProduto&nomeProduto=" + nomeProduto).forward(req, resp);
            }
        }

        if ("venda".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            System.out.println("EU ESTIVE AQUI");
            if ("efetuar".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=efetuar").forward(req, resp);
            } else if ("cancelar".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=cancelar").forward(req, resp);
            } else if ("buscaProduto".equalsIgnoreCase(tipo)) {
                String idCliente = req.getParameter("idCliente");
                String idProduto = req.getParameter("idProduto");
                req.getRequestDispatcher("venda?tipo=buscaProduto&idProduto=" + idProduto + "&idCliente=" + idCliente).forward(req, resp);
            } else if ("buscaCliente".equalsIgnoreCase(tipo)) {
                String idCliente = req.getParameter("idCliente");
                System.out.println("EU ESTIVE AQUI");
                req.getRequestDispatcher("venda?tipo=buscaCliente&id=" + idCliente).forward(req, resp);
            } else {
                req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
            }
        }

        if ("orcamento".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("gerar".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=gerar").forward(req, resp);
            } else if ("salvos".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=salvos").forward(req, resp);
            }
        }

        if ("relatorio".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("venda".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("relatorio?tipo=venda").forward(req, resp);
            } else if ("estoque".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("relatorio?tipo=estoque").forward(req, resp);
            }
        }

        if ("pesquisa".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("cliente".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("pesquisa?tipo=cliente").forward(req, resp);
            } else if ("produto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("pesquisa?tipo=produto").forward(req, resp);
            }
        }
    }
}
