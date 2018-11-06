package br.edu.fapi.dkrt.business.endereco.impl;

import br.edu.fapi.dkrt.business.endereco.EnderecoBusiness;

public class EnderecoBusinessImpl implements EnderecoBusiness {

    @Override
    public boolean verificarNumero(String numero) {
        for (Character c : numero.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
