package br.edu.fapi.dkrt.dao.cliente.impl;

import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;
import br.edu.fapi.dkrt.model.uf.UfDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {


    public int createCliente(ClienteDTO clienteDTO) {
        int idCliente = 0;
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clientes (cliente_nome, cliente_nomeSocial, " +
                    "cliente_rg, cliente_cpf, cliente_dtNasc, cliente_email, cliente_celular, cliente_telefone, cliente_ativo, " +
                    "cliente_dataDeCadastro, cliente_observacao, endereco_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, clienteDTO.getNome());
            preparedStatement.setString(2, clienteDTO.getNomeSocial());
            preparedStatement.setString(3, clienteDTO.getRg());
            preparedStatement.setString(4, clienteDTO.getCpf());
            preparedStatement.setDate(5, new java.sql.Date(clienteDTO.getDtNascimento().getTime()));
            preparedStatement.setString(6, clienteDTO.getEmail());
            preparedStatement.setString(7, clienteDTO.getCelular());
            preparedStatement.setString(8, clienteDTO.getTelefone());
            if (clienteDTO.isAtivo()){
                preparedStatement.setInt(9, 1);
            } else {
                preparedStatement.setInt(9, 0);
            }
            preparedStatement.setDate(10, new java.sql.Date(clienteDTO.getDataCadastro().getTime()));
            preparedStatement.setString(11, clienteDTO.getObservacao());
            preparedStatement.setInt(12, clienteDTO.getEnderecoDTO().getId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                idCliente = resultSet.getInt(1);
            }
            return idCliente;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Falha na conexao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        List<ClienteDTO> listaClientes = new ArrayList<>();
        String sql = "SELECT clientes.cliente_id, clientes.cliente_nome, clientes.cliente_nomeSocial, clientes.cliente_rg, clientes.cliente_cpf, " +
                "clientes.cliente_dtNasc, clientes.cliente_email, clientes.cliente_celular, clientes.cliente_telefone, clientes.cliente_ativo, " +
                "clientes.cliente_dataDeCadastro, clientes.cliente_dataDeAlteracao, clientes.cliente_observacao, enderecos.endereco_id, " +
                "enderecos.endereco_cep, enderecos.endereco_rua, enderecos.endereco_numero, enderecos.endereco_complemento, enderecos.endereco_bairro, " +
                "enderecos.endereco_cidade, ufs.uf_id, ufs.uf_sigla, ufs.uf_nome FROM clientes INNER JOIN enderecos ON clientes.endereco_id = enderecos.endereco_id " +
                "INNER JOIN ufs ON enderecos.uf_id = ufs.uf_id";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UfDTO ufDTO = new UfDTO();
                ufDTO.setId(resultSet.getInt("uf_id"));
                ufDTO.setSigla(resultSet.getString("uf_sigla"));
                ufDTO.setNome(resultSet.getString("uf_nome"));
                EnderecoDTO enderecoDTO = new EnderecoDTO();
                enderecoDTO.setUfDTO(ufDTO);
                enderecoDTO.setId(resultSet.getInt("endereco_id"));
                enderecoDTO.setCep(resultSet.getString("endereco_cep"));
                enderecoDTO.setRua(resultSet.getString("endereco_rua"));
                enderecoDTO.setNumero(resultSet.getString("endereco_numero"));
                enderecoDTO.setComplemento(resultSet.getString("endereco_complemento"));
                enderecoDTO.setBairro(resultSet.getString("endereco_bairro"));
                enderecoDTO.setCidade(resultSet.getString("endereco_cidade"));
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setEnderecoDTO(enderecoDTO);
                clienteDTO.setId(resultSet.getInt("cliente_id"));
                clienteDTO.setNome(resultSet.getString("cliente_nome"));
                clienteDTO.setNomeSocial(resultSet.getString("cliente_nomeSocial"));
                clienteDTO.setRg(resultSet.getString("cliente_rg"));
                clienteDTO.setCpf(resultSet.getString("cliente_cpf"));
                clienteDTO.setDtNascimento(resultSet.getDate("cliente_dtNasc"));
                clienteDTO.setEmail(resultSet.getString("cliente_email"));
                clienteDTO.setCelular(resultSet.getString("cliente_celular"));
                clienteDTO.setTelefone(resultSet.getString("cliente_telefone"));
                clienteDTO.setAtivo((resultSet.getInt("cliente_ativo") == 1) ? true : false);
                clienteDTO.setDataCadastro(resultSet.getDate("cliente_dataDeCadastro"));
                clienteDTO.setDataAlteracao(resultSet.getDate("cliente_dataDeAlteracao"));
                clienteDTO.setObservacao(resultSet.getString("cliente_observacao"));
                listaClientes.add(clienteDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    @Override
    public ClienteDTO buscarCliente(int id) {
        ClienteDTO clienteBusca = new ClienteDTO();
        String sql = "SELECT clientes.cliente_id, clientes.cliente_nome, clientes.cliente_nomeSocial, clientes.cliente_rg, clientes.cliente_cpf, " +
                "clientes.cliente_dtNasc, clientes.cliente_email, clientes.cliente_celular, clientes.cliente_telefone, clientes.cliente_ativo, " +
                "clientes.cliente_dataDeCadastro, clientes.cliente_dataDeAlteracao, clientes.cliente_observacao, enderecos.endereco_id, " +
                "enderecos.endereco_cep, enderecos.endereco_rua, enderecos.endereco_numero, enderecos.endereco_complemento, enderecos.endereco_bairro, " +
                "enderecos.endereco_cidade, ufs.uf_id, ufs.uf_sigla, ufs.uf_nome FROM clientes INNER JOIN enderecos ON clientes.endereco_id = enderecos.endereco_id " +
                "INNER JOIN ufs ON enderecos.uf_id = ufs.uf_id WHERE cliente_id = ?";

        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                UfDTO ufDTO = new UfDTO();
                ufDTO.setId(resultSet.getInt("uf_id"));
                ufDTO.setSigla(resultSet.getString("uf_sigla"));
                ufDTO.setNome(resultSet.getString("uf_nome"));
                EnderecoDTO enderecoDTO = new EnderecoDTO();
                enderecoDTO.setUfDTO(ufDTO);
                enderecoDTO.setId(resultSet.getInt("endereco_id"));
                enderecoDTO.setCep(resultSet.getString("endereco_cep"));
                enderecoDTO.setRua(resultSet.getString("endereco_rua"));
                enderecoDTO.setNumero(resultSet.getString("endereco_numero"));
                enderecoDTO.setComplemento(resultSet.getString("endereco_complemento"));
                enderecoDTO.setBairro(resultSet.getString("endereco_bairro"));
                enderecoDTO.setCidade(resultSet.getString("endereco_cidade"));
                clienteBusca.setEnderecoDTO(enderecoDTO);
                clienteBusca.setId(resultSet.getInt("cliente_id"));
                clienteBusca.setNome(resultSet.getString("cliente_nome"));
                clienteBusca.setNomeSocial(resultSet.getString("cliente_nomeSocial"));
                clienteBusca.setRg(resultSet.getString("cliente_rg"));
                clienteBusca.setCpf(resultSet.getString("cliente_cpf"));
                clienteBusca.setDtNascimento(resultSet.getDate("cliente_dtNasc"));
                clienteBusca.setEmail(resultSet.getString("cliente_email"));
                clienteBusca.setCelular(resultSet.getString("cliente_celular"));
                clienteBusca.setTelefone(resultSet.getString("cliente_telefone"));
                clienteBusca.setAtivo((resultSet.getInt("cliente_ativo") == 1) ? true : false);
                clienteBusca.setDataCadastro(resultSet.getDate("cliente_dataDeCadastro"));
                clienteBusca.setDataAlteracao(resultSet.getDate("cliente_dataDeAlteracao"));
                clienteBusca.setObservacao(resultSet.getString("cliente_observacao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clienteBusca;
    }
}
