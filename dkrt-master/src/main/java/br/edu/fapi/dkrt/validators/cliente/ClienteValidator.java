package br.edu.fapi.dkrt.validators.cliente;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

public interface ClienteValidator {

    String verificarCliente(ClienteDTO clienteDTO);

}
