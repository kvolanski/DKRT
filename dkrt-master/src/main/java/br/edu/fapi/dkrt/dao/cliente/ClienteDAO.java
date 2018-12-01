package br.edu.fapi.dkrt.dao.cliente;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

import java.util.List;

public interface ClienteDAO {

    int createCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> listarClientes();

    ClienteDTO buscarCliente(int id);

    List<ClienteDTO> listarClientePesquisa(String sql);

    boolean adicionaCompraCliente(ClienteDTO clienteDTO);

    boolean excluirCliente(int id);

    boolean editarCliente(ClienteDTO clienteDTO);
}
