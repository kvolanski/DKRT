package br.edu.fapi.dkrt.business.login.impl;

import br.edu.fapi.dkrt.business.login.LoginBusiness;
import br.edu.fapi.dkrt.dao.usuario.UsuarioDAO;
import br.edu.fapi.dkrt.dao.usuario.impl.UsuarioDAOImpl;
import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

public class LoginBusinessImpl implements LoginBusiness {
    UsuarioDAO usuarioDAO;

    public LoginBusinessImpl() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public boolean validaLogin(UsuarioDTO usuarioDTO) {
        if (usuarioDAO.validarUsuario(usuarioDTO)) {
            return true;
        }
        return false;
    }
}
