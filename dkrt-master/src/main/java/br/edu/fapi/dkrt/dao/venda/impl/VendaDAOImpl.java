package br.edu.fapi.dkrt.dao.venda.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAOImpl implements VendaDAO {

    @Override
    public int abrirVenda(VendaDTO vendaDTO) {
        int id = 0;
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vendas (venda_status, cliente_id) VALUES " +
                    "(?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, vendaDTO.getStatus());
            preparedStatement.setInt(2, vendaDTO.getClienteDTO().getId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
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

    @Override
    public int adicionarPedido(PedidoDTO pedidoDTO, String tipo) {
        int id = 0;
        String sql;
        if ("venda".equalsIgnoreCase(tipo)) {
            sql = "INSERT INTO pedidos (produto_id, pedido_quantidade, pedido_valorUnitario, pedido_valorTotal, venda_id) VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO pedidos (produto_id, pedido_quantidade, pedido_valorUnitario, pedido_valorTotal, orcamento_id) VALUES (?, ?, ?, ?, ?)";
        }
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, pedidoDTO.getProdutoDTO().getId());
            preparedStatement.setInt(2, pedidoDTO.getQuantidade());
            preparedStatement.setFloat(3, pedidoDTO.getValorUnitario());
            preparedStatement.setFloat(4, pedidoDTO.getValorTotal());
            if ("venda".equalsIgnoreCase(tipo)) {
                preparedStatement.setInt(5, pedidoDTO.getVendaDTO().getId());
            } else {
                preparedStatement.setInt(5, pedidoDTO.getOrcamentoDTO().getId());
            }

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
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
    public List<PedidoDTO> listarPedidosVenda(int id, String tipo) {
        List<PedidoDTO> listaPedido = new ArrayList<>();
        String sql;
        if ("venda".equalsIgnoreCase(tipo)) {
            sql = "SELECT pedidos.pedido_id, produtos.produto_id, produtos.produto_nome, produtos.produto_descricao, pedidos.pedido_quantidade, " +
                    "pedidos.pedido_valorUnitario, pedidos.pedido_valorTotal FROM pedidos INNER JOIN produtos ON pedidos.produto_id = produtos.produto_id " +
                    "WHERE pedidos.venda_id = ?";
        } else {
            sql = "SELECT pedidos.pedido_id, produtos.produto_id, produtos.produto_nome, produtos.produto_descricao, pedidos.pedido_quantidade, " +
                    "pedidos.pedido_valorUnitario, pedidos.pedido_valorTotal FROM pedidos INNER JOIN produtos ON pedidos.produto_id = produtos.produto_id " +
                    "WHERE pedidos.orcamento_id = ?";
        }
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ProdutoDTO produtoDTO = new ProdutoDTO();
                produtoDTO.setId(resultSet.getInt("produto_id"));
                produtoDTO.setNome(resultSet.getString("produto_nome"));
                produtoDTO.setDescricao(resultSet.getString("produto_descricao"));
                PedidoDTO pedidoDTO = new PedidoDTO();
                pedidoDTO.setProdutoDTO(produtoDTO);
                pedidoDTO.setId(resultSet.getInt("pedido_id"));
                pedidoDTO.setQuantidade(resultSet.getInt("pedido_quantidade"));
                pedidoDTO.setValorUnitario(resultSet.getFloat("pedido_valorUnitario"));
                pedidoDTO.setValorTotal(resultSet.getFloat("pedido_valorTotal"));
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
}
