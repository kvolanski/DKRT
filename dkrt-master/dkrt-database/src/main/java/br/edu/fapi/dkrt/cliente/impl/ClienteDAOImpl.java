package br.edu.fapi.dkrt.cliente.impl;

import br.edu.fapi.dkrt.cliente.ClienteDAO;
import br.edu.fapi.dkrt.cliente.ClienteDTO;
import br.edu.fapi.dkrt.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.endereco.EnderecoDTO;

import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO {


    public int createCliente(ClienteDTO clienteDTO) {
        int idCliente;
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliente (cliente_nome,cliente_nomeSocial,cliente_cpf,cliente_telefone,cliente_ativo," +
                    ",dataCadastroCliente,dataAlteracaoCliente) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, clienteDTO.getNome());
            preparedStatement.setString(2, clienteDTO.getNomeSocial());
            preparedStatement.setString(3, clienteDTO.getCpf());
            preparedStatement.setString(4, clienteDTO.getTelefone());
            preparedStatement.setBoolean(5, clienteDTO.isAtivo());
            preparedStatement.setDate(6, (Date) clienteDTO.getDataCadastroCliente());
            preparedStatement.setDate(7, (Date) clienteDTO.getDataAlteracaoCliente());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            idCliente = resultSet.getInt(1);

            return idCliente;

        } catch (SQLException e) {
            System.out.println("Falha na conexao");
        }

        return 0;
    }

    @Override
    public int createEndereco(ClienteDTO clienteDTO, int idCliente) {

        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO endereco (endereco_cep,endereco_rua,endereco_numero,endereco_complemento,endereco_bairro,endereco_cidade,endereco_estado, cliente_id)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, clienteDTO.getEndereco().getCep());
            preparedStatement.setString(2, clienteDTO.getEndereco().getRua());
            preparedStatement.setString(3, clienteDTO.getEndereco().getNumero());
            preparedStatement.setString(4, clienteDTO.getEndereco().getComplemento());
            preparedStatement.setString(5, clienteDTO.getEndereco().getBairro());
            preparedStatement.setString(6, clienteDTO.getEndereco().getCidade());
            preparedStatement.setString(7, clienteDTO.getEndereco().getEstado());
            preparedStatement.setInt(8, idCliente);

            preparedStatement.executeUpdate();

            return 1;

        } catch (SQLException e) {
            System.out.println("Falha na conexao");
        }

        return 0;
    }
}
