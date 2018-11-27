package br.edu.fapi.dkrt.dao.orcamento.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.orcamento.OrcamentoDAO;
import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAOImpl implements OrcamentoDAO {

    @Override
    public int abrirOrcamento(OrcamentoDTO orcamentoDTO) {
        int id = 0;
        String sql = "INSERT INTO orcamentos (orcamento_status, orcamento_dataGeracao, cliente_id) VALUES (?, ?, ?)";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, orcamentoDTO.getStatus());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(orcamentoDTO.getDataGeracao().getTime()));
            preparedStatement.setInt(3, orcamentoDTO.getClienteDTO().getId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
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
        String sql = "INSERT INTO pedidos (produto_id, pedido_quantidade, pedido_valorUnitario, pedido_valorTotal, orcamento_id) VALUES " +
                "(?, ?, ?, ?, ?)";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, pedidoDTO.getProdutoDTO().getId());
            preparedStatement.setInt(2, pedidoDTO.getQuantidade());
            preparedStatement.setFloat(3, pedidoDTO.getValorUnitario());
            preparedStatement.setFloat(4, pedidoDTO.getValorTotal());
            preparedStatement.setInt(5, pedidoDTO.getOrcamentoDTO().getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PedidoDTO> listarPedidosOrcamento(int id) {
        List<PedidoDTO> listaPedido = new ArrayList<>();
        String sql = "SELECT pedidos.pedido_id, produtos.produto_id, produtos.produto_nome, produtos.produto_descricao, produtos.produto_qtdEstoque, pedidos.pedido_quantidade, " +
                "pedidos.pedido_valorUnitario, pedidos.pedido_valorTotal FROM pedidos INNER JOIN produtos ON pedidos.produto_id = produtos.produto_id " +
                "WHERE pedidos.orcamento_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PedidoDTO pedidoDTO = fillPedido(resultSet);
                listaPedido.add(pedidoDTO);
            }

            return listaPedido;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaPedido;
    }

    @Override
    public boolean finalizarOrcamento(OrcamentoDTO orcamentoDTO) {
        String sql = "";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private PedidoDTO fillPedido(ResultSet resultSet) throws SQLException {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(resultSet.getInt("produto_id"));
        produtoDTO.setNome(resultSet.getString("produto_nome"));
        produtoDTO.setDescricao(resultSet.getString("produto_descricao"));
        produtoDTO.setQtdEstoque(resultSet.getInt("produto_qtdEstoque"));
        PedidoDTO pedidoBusca = new PedidoDTO();
        pedidoBusca.setProdutoDTO(produtoDTO);
        pedidoBusca.setId(resultSet.getInt("pedido_id"));
        pedidoBusca.setQuantidade(resultSet.getInt("pedido_quantidade"));
        pedidoBusca.setValorUnitario(resultSet.getFloat("pedido_valorUnitario"));
        pedidoBusca.setValorTotal(resultSet.getFloat("pedido_valorTotal"));
        return pedidoBusca;
    }
}
