package br.edu.fapi.dkrt.dao.usuario;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

public interface UsuarioDAO {

    boolean validarUsuario(UsuarioDTO usuarioDTO);

    int createUsuario(UsuarioDTO usuario);

    UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO);

}
