package br.edu.fapi.dkrt.dao.cliente.impl;

import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO {


    public int createCliente(ClienteDTO clienteDTO) {
        int idCliente = 0;
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clientes (cliente_nome, cliente_cpf, cliente_dtNasc, cliente_telefone, cliente_ativo, " +
                    " cliente_dataDeCadastro, endereco_id) VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, clienteDTO.getNome());
            preparedStatement.setString(2, clienteDTO.getCpf());
            preparedStatement.setDate(3, new java.sql.Date(clienteDTO.getDtNascimento().getTime()));
            preparedStatement.setString(4, clienteDTO.getTelefone());
            if (clienteDTO.isAtivo()){
                preparedStatement.setInt(5, 1);
            } else {
                preparedStatement.setInt(5, 0);
            }
            preparedStatement.setDate(6, new java.sql.Date(clienteDTO.getDataCadastroCliente().getTime()));
            preparedStatement.setInt(7, clienteDTO.getEnderecoDTO().getId());

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
}
