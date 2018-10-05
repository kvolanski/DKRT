package br.edu.fapi.dkrt.business.usuario.impl;

import br.edu.fapi.dkrt.business.usuario.UsuarioBusiness;
import br.edu.fapi.dkrt.usuario.UsuarioDAO;
import br.edu.fapi.dkrt.usuario.UsuarioDTO;
import br.edu.fapi.dkrt.usuario.impl.UsuarioDAOImpl;

public class UsuarioBusinessImpl implements UsuarioBusiness {

    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    public UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO){
        return usuarioDAO.buscarUsuario(usuarioDTO);
    }

}
