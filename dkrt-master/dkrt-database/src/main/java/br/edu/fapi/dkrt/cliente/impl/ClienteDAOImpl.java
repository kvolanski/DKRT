package br.edu.fapi.dkrt.cliente.impl;

import br.edu.fapi.dkrt.cliente.ClienteDAO;
import br.edu.fapi.dkrt.cliente.ClienteDTO;
import br.edu.fapi.dkrt.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.endereco.EnderecoDTO;

import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO {


    public int createCliente(ClienteDTO clienteDTO) {
        try(Connection connection = MySqlConnectionProvider.abrirConexao()){

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliente (nome,nomeSocial,cpf,telefone,ativo," +
                    ",dataCadastroCliente,dataAlteracaoCliente) " +
                    "VALUES(?,?,?,?,?)" , Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,clienteDTO.getNome());
            preparedStatement.setString(2,clienteDTO.getNomeSocial());
            preparedStatement.setString(3,clienteDTO.getCpf());
            preparedStatement.setString(4,clienteDTO.getTelefone());
            preparedStatement.setBoolean(5,clienteDTO.isAtivo());
            preparedStatement.setDate(6, (Date) clienteDTO.getDataCadastroCliente());
            preparedStatement.setDate(7,(Date) clienteDTO.getDataAlteracaoCliente());


        }catch (SQLException e){
            e.getMessage();
        }

        return 0;
    }

    @Override
    public int createEndereco(EnderecoDTO enderecoDTO) {

        try(Connection connection = MySqlConnectionProvider.abrirConexao()){

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO endereco (cep,rua,numero,complemento,bairro,cidade,estado)" +
                            "VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,enderecoDTO.getCep());
            preparedStatement.setString(2,enderecoDTO.getRua());
            preparedStatement.setString(3,enderecoDTO.getNumero());
            preparedStatement.setString(4,enderecoDTO.getComplemento());
            preparedStatement.setString(5,enderecoDTO.getBairro());
            preparedStatement.setString(6,enderecoDTO.getCidade());
            preparedStatement.setString(7,enderecoDTO.getEstado());


        }catch (SQLException e){
            e.getMessage();
        }



        return 0;
    }
}
