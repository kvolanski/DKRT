package br.edu.fapi.dkrt.usuario;

public interface UsuarioDAO {

    int createUsuario(UsuarioDTO usuario);

    UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO);

}
