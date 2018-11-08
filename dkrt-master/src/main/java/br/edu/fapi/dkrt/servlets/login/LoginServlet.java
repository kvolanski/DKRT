package br.edu.fapi.dkrt.servlets.login;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;
import br.edu.fapi.dkrt.business.login.impl.LoginBusinessImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginBusinessImpl loginBusinessImpl = new LoginBusinessImpl();
        String email = req.getParameter("loginUsuario");
        String senha = req.getParameter("senhaUsuario");

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(email);
        usuarioDTO.setSenha(senha);

        if (loginBusinessImpl.validaLogin(usuarioDTO)) {
            req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

}
