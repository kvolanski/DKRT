package br.edu.fapi.dkrt.business.cliente.impl;

import br.edu.fapi.dkrt.business.cliente.ClienteBusiness;

public class ClienteBusinessImpl implements ClienteBusiness {

    @Override
    public boolean verificarNumero(String numero) {
        for (Character c : numero.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verificarNumeroPalavra(String palavra) {
        for (Character c : palavra.toCharArray()){
            if (Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
