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

            req.getRequestDispatcher("/login").forward(req, resp);
        }

        if ("excluir".equalsIgnoreCase(acao)){
            req.getRequestDispatcher("/excluir").forward(req, resp);
        }
    }
}
