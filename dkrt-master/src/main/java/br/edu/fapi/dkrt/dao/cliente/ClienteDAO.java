package br.edu.fapi.dkrt.dao.cliente;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

import java.util.List;

public interface ClienteDAO {

    int createCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> listarClientes();

    ClienteDTO buscarCliente(int id);

    List<ClienteDTO> pesquisarClienteLikeNome(String palavra);

    boolean adicionaCompraCliente(ClienteDTO clienteDTO);
}
