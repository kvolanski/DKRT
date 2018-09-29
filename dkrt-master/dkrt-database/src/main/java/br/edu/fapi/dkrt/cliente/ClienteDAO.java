package br.edu.fapi.dkrt.cliente;

import br.edu.fapi.dkrt.endereco.EnderecoDTO;

import java.io.IOException;

public interface ClienteDAO {

    int createCliente (ClienteDTO clienteDTO);

    int createEndereco (EnderecoDTO enderecoDTO);


}
