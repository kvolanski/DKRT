package br.edu.fapi.dkrt.dao.cliente.impl;

import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;

import java.sql.*;

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
            preparedStatement.setDate(10, new java.sql.Date(clienteDTO.getDataCadastroCliente().getTime()));
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
}
