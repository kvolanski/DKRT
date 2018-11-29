package br.edu.fapi.dkrt.dao.orcamento.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.orcamento.OrcamentoDAO;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
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
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, pedidoDTO.getProdutoDTO().getId());
            preparedStatement.setInt(2, pedidoDTO.getQuantidade());
            preparedStatement.setFloat(3, pedidoDTO.getValorUnitario());
            preparedStatement.setFloat(4, pedidoDTO.getValorTotal());
            preparedStatement.setInt(5, pedidoDTO.getOrcamentoDTO().getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0) {
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
        String sql;
        if (orcamentoDTO.getDataExpiracao() != null) {
            sql = "UPDATE orcamentos SET orcamento_valorTotal = ?, orcamento_status = ?, orcamento_desconto = ?, orcamento_dataExpiracao = ? WHERE orcamento_id = ?";
        } else {
            sql = "UPDATE orcamentos SET orcamento_valorTotal = ?, orcamento_status = ?, orcamento_desconto = ? WHERE orcamento_id = ?";
        }
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setFloat(1, orcamentoDTO.getValorTotal());
            preparedStatement.setString(2, orcamentoDTO.getStatus());
            preparedStatement.setInt(3, orcamentoDTO.getDesconto());
            if (orcamentoDTO.getDataExpiracao() != null) {
                preparedStatement.setDate(4, new java.sql.Date(orcamentoDTO.getDataExpiracao().getTime()));
                preparedStatement.setInt(5, orcamentoDTO.getId());
            } else {
                preparedStatement.setInt(4, orcamentoDTO.getId());
            }

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0) {
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
    public List<OrcamentoDTO> listarOrcamentos() {
        List<OrcamentoDTO> listaOrcamentos = new ArrayList<>();
        String sql = "SELECT orcamentos.orcamento_id, orcamentos.orcamento_valorTotal, orcamentos.orcamento_status, orcamentos.orcamento_desconto, " +
                "orcamentos.orcamento_dataGeracao, orcamentos.orcamento_dataExpiracao, clientes.cliente_id, clientes.cliente_nome, clientes.cliente_email, " +
                "clientes.cliente_celular FROM orcamentos INNER JOIN clientes ON orcamentos.cliente_id = clientes.cliente_id ORDER BY orcamentos.orcamento_id";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrcamentoDTO orcamentoDTO = fillOrcamento(resultSet);
                listaOrcamentos.add(orcamentoDTO);
            }

            return listaOrcamentos;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaOrcamentos;
    }

    @Override
    public boolean excluirOrcamento(int id) {
        String sql = "DELETE FROM orcamentos WHERE orcamento_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0) {
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
    public boolean adicionaIdVendaOrcamento(int idOrcamento, int idVenda) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pedidos SET venda_id = ? WHERE orcamento_id = ?");

            preparedStatement.setInt(1, idVenda);
            preparedStatement.setInt(2, idOrcamento);

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
    public OrcamentoDTO buscarOrcamento(int id) {
        String sql = "SELECT orcamentos.orcamento_id, orcamentos.orcamento_valorTotal, orcamentos.orcamento_status, orcamentos.orcamento_desconto, " +
                "orcamentos.orcamento_dataGeracao, orcamentos.orcamento_dataExpiracao, clientes.cliente_id, clientes.cliente_nome, clientes.cliente_email, " +
                "clientes.cliente_celular FROM orcamentos INNER JOIN clientes ON orcamentos.cliente_id = clientes.cliente_id WHERE orcamentos.orcamento_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                OrcamentoDTO orcamentoDTO = fillOrcamento(resultSet);
                return orcamentoDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean atualizarStatusOrcamento(OrcamentoDTO orcamentoDTO) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orcamentos SET orcamento_status = ? WHERE orcamento_id = ?");

            preparedStatement.setString(1, orcamentoDTO.getStatus());
            preparedStatement.setInt(2, orcamentoDTO.getId());

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

    private OrcamentoDTO fillOrcamento(ResultSet resultSet) throws SQLException {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(resultSet.getInt("cliente_id"));
        clienteDTO.setNome(resultSet.getString("cliente_nome"));
        clienteDTO.setEmail(resultSet.getString("cliente_email"));
        clienteDTO.setCelular(resultSet.getString("cliente_celular"));
        OrcamentoDTO orcamentoBusca = new OrcamentoDTO();
        orcamentoBusca.setClienteDTO(clienteDTO);
        orcamentoBusca.setId(resultSet.getInt("orcamento_id"));
        orcamentoBusca.setValorTotal(resultSet.getFloat("orcamento_valorTotal"));
        orcamentoBusca.setStatus(resultSet.getString("orcamento_status"));
        orcamentoBusca.setDesconto(resultSet.getInt("orcamento_desconto"));
        orcamentoBusca.setDataGeracao(resultSet.getTimestamp("orcamento_dataGeracao"));
        orcamentoBusca.setDataExpiracao(resultSet.getDate("orcamento_dataExpiracao"));
        return orcamentoBusca;
    }
}
