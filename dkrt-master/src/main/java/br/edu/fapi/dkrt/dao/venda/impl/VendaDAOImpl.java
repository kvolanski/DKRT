package br.edu.fapi.dkrt.dao.venda.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.sql.*;

public class VendaDAOImpl implements VendaDAO {

    @Override
    public int abrirVenda(VendaDTO vendaDTO) {
        int id = 0;
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vendas (venda_status, cliente_id) VALUES " +
                    "(?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, vendaDTO.getStatus());
            preparedStatement.setInt(2, vendaDTO.getClienteDTO().getId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                id = resultSet.getInt(1);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }
}
