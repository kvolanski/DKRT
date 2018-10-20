package br.edu.fapi.dkrt.business.usuario.impl;

import br.edu.fapi.dkrt.business.usuario.UsuarioBusiness;
import br.edu.fapi.dkrt.dao.usuario.UsuarioDAO;
import br.edu.fapi.dkrt.dao.usuario.impl.UsuarioDAOImpl;
import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

public class UsuarioBusinessImpl implements UsuarioBusiness {

    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    public UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioDAO.buscarUsuario(usuarioDTO);
    }

}
