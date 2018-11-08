package br.edu.fapi.dkrt.validators.cliente.impl;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.validators.cliente.ClienteValidator;

public class ClienteValidatorImpl implements ClienteValidator {

    @Override
    public String verificarCliente(ClienteDTO clienteDTO) {
        String condicao = "sucesso";
        if (!verificarNumero(clienteDTO.getEnderecoDTO().getNumero())) {
            condicao = "numEndereco ";
        }

        if (!verificarNumero(clienteDTO.getEnderecoDTO().getCep())) {
            if ("sucesso".equalsIgnoreCase(condicao)) {
                condicao = "cepEndereco ";
            } else {
                condicao += "cepEndereco ";
            }
        }

        if (!verificarNumero(clienteDTO.getTelefone())) {
            if ("sucesso".equalsIgnoreCase(condicao)) {
                condicao = "telefoneCliente ";
            } else {
                condicao += "telefoneCliente ";
            }
        }

        if (!verificarNumero(clienteDTO.getCpf())) {
            if ("sucesso".equalsIgnoreCase(condicao)) {
                condicao = "cpfCliente ";
            } else {
                condicao += "cpfCliente ";
            }
        }

        if (!verificarNumeroPalavra(clienteDTO.getNome())) {
            if ("sucesso".equalsIgnoreCase(condicao)) {
                condicao = "nomeCliente ";
            } else {
                condicao += "nomeCliente ";
            }
        }

        if (clienteDTO.getEnderecoDTO().getUfDTO().getId() == 0) {
            if ("sucesso".equalsIgnoreCase(condicao)) {
                condicao = "ufIncorreto ";
            } else {
                condicao += "ufIncorreto ";
            }
        }
        return condicao;
    }

    private boolean verificarNumero(String numero) {
        for (Character c : numero.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarNumeroPalavra(String palavra) {
        for (Character c : palavra.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
