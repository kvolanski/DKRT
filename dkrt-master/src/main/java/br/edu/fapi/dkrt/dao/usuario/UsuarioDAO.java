package br.edu.fapi.dkrt.dao.usuario;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

public interface UsuarioDAO {

    int createUsuario(UsuarioDTO usuario);

    UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO);

}
