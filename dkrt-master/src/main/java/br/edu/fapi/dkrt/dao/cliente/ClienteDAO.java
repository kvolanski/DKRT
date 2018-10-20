package br.edu.fapi.dkrt.dao.cliente;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

public interface ClienteDAO {

    int createCliente(ClienteDTO clienteDTO);

    int createEndereco(ClienteDTO clienteDTO, int idCliente);


}
