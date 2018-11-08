package br.edu.fapi.dkrt.business.login;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

public interface LoginBusiness {

    boolean validaLogin(UsuarioDTO usuarioDTO);

}
