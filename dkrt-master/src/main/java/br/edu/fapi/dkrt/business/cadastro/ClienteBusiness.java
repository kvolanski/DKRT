package br.edu.fapi.dkrt.business.cadastro;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

import java.util.List;

public interface ClienteBusiness {

    String cadastrarCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> pesquisarClienteLikeNome(String palavra);

    ClienteDTO buscarClienteId(int id);

}
