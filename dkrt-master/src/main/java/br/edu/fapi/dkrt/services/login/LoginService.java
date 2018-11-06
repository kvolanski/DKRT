package br.edu.fapi.dkrt.services.login;

import br.edu.fapi.dkrt.dao.usuario.UsuarioDAO;
import br.edu.fapi.dkrt.dao.usuario.impl.UsuarioDAOImpl;
import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

public class LoginService {
    UsuarioDAO usuarioDAO;

    public LoginService() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean validaLogin(UsuarioDTO usuarioDTO) {
        if (usuarioDAO.validarUsuario(usuarioDTO)) {
            return true;
        }
        return false;
    }
}
