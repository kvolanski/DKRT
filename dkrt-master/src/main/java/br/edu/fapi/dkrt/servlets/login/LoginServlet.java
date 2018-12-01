package br.edu.fapi.dkrt.servlets.login;

import br.edu.fapi.dkrt.business.login.impl.LoginBusinessImpl;
import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;
import br.edu.fapi.dkrt.servlets.AbstractBaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends AbstractBaseHttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("logar".equalsIgnoreCase(tipo)) {
            LoginBusinessImpl loginBusinessImpl = new LoginBusinessImpl();
            String email = req.getParameter("loginUsuario");
            String senha = req.getParameter("senhaUsuario");

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setEmail(email);
            usuarioDTO.setSenha(senha);

            if (loginBusinessImpl.validaLogin(usuarioDTO)) {
                req.getSession().setAttribute("usuario.session", usuarioDTO);
                req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
            } else {
                setSessionAttribute(req, "erro", "sim");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("deslogar".equalsIgnoreCase(tipo)) {
            req.getSession().removeAttribute("usuario.session");
            setSessionAttribute(req, "erro", "nao");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
