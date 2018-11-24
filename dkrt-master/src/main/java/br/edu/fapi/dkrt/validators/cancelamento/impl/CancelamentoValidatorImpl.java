package br.edu.fapi.dkrt.validators.cancelamento.impl;

import br.edu.fapi.dkrt.validators.cancelamento.CancelamentoValidator;

import java.util.regex.Pattern;

public class CancelamentoValidatorImpl implements CancelamentoValidator {

    @Override
    public boolean validaMotivo(String motivo) {
        if(Pattern.compile("[^a-z0-9\\ .,]").matcher(motivo).find()){
            return false;
        }
        return true;
    }
}
