package br.edu.fapi.dkrt.servlets.login;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

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
        String email = req.getParameter("usuario");
        String senha = req.getParameter("senha");


//        UsuarioDTO usuarioDTO = new UsuarioDTO();
//        usuarioDTO.setEmail(email);
//        usuarioDTO.setSenha(senha);

        req.getRequestDispatcher("WEB-INF/menu.jsp").forward(req, resp);
    }

}
