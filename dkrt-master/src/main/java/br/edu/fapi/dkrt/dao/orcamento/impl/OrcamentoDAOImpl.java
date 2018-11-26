package br.edu.fapi.dkrt.dao.orcamento.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.orcamento.OrcamentoDAO;
import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;

import java.sql.*;

public class OrcamentoDAOImpl implements OrcamentoDAO {

    @Override
    public int abrirOrcamento(OrcamentoDTO orcamentoDTO) {
        int id = 0;
        String sql = "INSERT INTO orcamentos (orcamento_status, cliente_id) VALUES (?, ?)";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, orcamentoDTO.getStatus());
            preparedStatement.setInt(2, orcamentoDTO.getClienteDTO().getId());

            int resultado = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultado != 0) {
                id = resultSet.getInt(1);
            }

            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean adicionarPedido(PedidoDTO pedidoDTO) {
        return false;
    }
}
