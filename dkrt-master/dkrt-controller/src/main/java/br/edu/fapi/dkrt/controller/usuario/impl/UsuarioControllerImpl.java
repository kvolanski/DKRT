package br.edu.fapi.dkrt.controller.usuario.impl;

import br.edu.fapi.dkrt.business.usuario.UsuarioBusiness;
import br.edu.fapi.dkrt.business.usuario.impl.UsuarioBusinessImpl;
import br.edu.fapi.dkrt.controller.usuario.UsuarioController;
import br.edu.fapi.dkrt.usuario.UsuarioDTO;

public class UsuarioControllerImpl implements UsuarioController {

    UsuarioBusiness usuarioBusiness = new UsuarioBusinessImpl();

    public UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioBusiness.buscarUsuario(usuarioDTO);
    }
}
