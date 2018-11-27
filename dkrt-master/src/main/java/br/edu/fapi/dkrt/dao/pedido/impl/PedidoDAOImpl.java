package br.edu.fapi.dkrt.dao.pedido.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.pedido.PedidoDAO;
import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAOImpl implements PedidoDAO {

    @Override
    public PedidoDTO buscaPedido(int id, String tipo) {
        PedidoDTO pedidoBusca = new PedidoDTO();
        String sql;
        if ("orcamento".equalsIgnoreCase(tipo)) {
            sql = "SELECT pedidos.pedido_id, pedidos.pedido_quantidade, pedidos.pedido_valorUnitario, pedidos.pedido_valorTotal, " +
                    "produtos.produto_id, produtos.produto_nome, produtos.produto_descricao, produtos.produto_precoVenda, produtos.produto_precoCusto, " +
                    "produtos.produto_qtdEstoque, produtos.produto_ativo, produtos.produto_dataCadastro, produtos.produto_dataAlteracao, orcamentos.orcamento_id, " +
                    "orcamentos.orcamento_valorTotal, orcamentos.orcamento_status, orcamentos.orcamento_desconto, orcamentos.orcamento_dataGeracao, orcamentos.orcamento_dataExpiracao " +
                    "FROM pedidos INNER JOIN produtos ON pedidos.produto_id = produtos.produto_id INNER JOIN orcamentos ON pedidos.orcamento_id = orcamentos.orcamento_id " +
                    "WHERE pedidos.pedido_id = ?";
        } else {
            sql = "SELECT pedidos.pedido_id, pedidos.pedido_quantidade, pedidos.pedido_valorUnitario, pedidos.pedido_valorTotal, " +
                    "produtos.produto_id, produtos.produto_nome, produtos.produto_descricao, produtos.produto_precoVenda, produtos.produto_precoCusto, " +
                    "produtos.produto_qtdEstoque, produtos.produto_ativo, produtos.produto_dataCadastro, produtos.produto_dataAlteracao, vendas.venda_id, " +
                    "vendas.venda_valorTotal, vendas.venda_formaDePagamento, vendas.venda_parcelas, vendas.venda_valorParcela, vendas.venda_status, " +
                    "vendas.venda_desconto, vendas.venda_dataDeVenda FROM pedidos INNER JOIN produtos ON pedidos.produto_id = produtos.produto_id " +
                    "INNER JOIN vendas ON pedidos.venda_id = vendas.venda_id WHERE pedidos.pedido_id = ?";
        }

        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pedidoBusca = fillPedido(resultSet, tipo);
            }

            return pedidoBusca;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pedidoBusca;
    }

    @Override
    public boolean retirarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE pedido_id = ? ";
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

    private PedidoDTO fillPedido(ResultSet resultSet, String tipo) throws SQLException {
        PedidoDTO pedidoBusca = new PedidoDTO();
        if ("orcamento".equalsIgnoreCase(tipo)) {
            OrcamentoDTO orcamentoDTO = new OrcamentoDTO();
            orcamentoDTO.setId(resultSet.getInt("orcamento_id"));
            orcamentoDTO.setValorTotal(resultSet.getFloat("orcamento_valorTotal"));
            orcamentoDTO.setStatus(resultSet.getString("orcamento_valorStatus"));
            orcamentoDTO.setDesconto(resultSet.getInt("orcamento_desconto"));
            orcamentoDTO.setDataGeracao(resultSet.getTimestamp("orcamento_dataGeracao"));
            orcamentoDTO.setDataExpiracao(resultSet.getTimestamp("orcamento_dataExpiracao"));
            pedidoBusca.setOrcamentoDTO(orcamentoDTO);
        } else {
            VendaDTO vendaDTO = new VendaDTO();
            vendaDTO.setId(resultSet.getInt("venda_id"));
            vendaDTO.setValorTotal(resultSet.getFloat("venda_valorTotal"));
            vendaDTO.setFormaDePagamento(resultSet.getString("venda_formaDePagamento"));
            vendaDTO.setParcelas(resultSet.getInt("venda_parcelas"));
            vendaDTO.setValorParcelas(resultSet.getFloat("venda_valorParcela"));
            vendaDTO.setStatus(resultSet.getString("venda_status"));
            vendaDTO.setDesconto(resultSet.getInt("venda_desconto"));
            vendaDTO.setDataDeVenda(resultSet.getTimestamp("venda_dataDeVenda"));
            pedidoBusca.setVendaDTO(vendaDTO);
        }
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(resultSet.getInt("produto_id"));
        produtoDTO.setNome(resultSet.getString("produto_nome"));
        produtoDTO.setDescricao(resultSet.getString("produto_descricao"));
        produtoDTO.setPrecoVenda(resultSet.getFloat("produto_precoVenda"));
        produtoDTO.setPrecoCusto(resultSet.getFloat("produto_precoCusto"));
        produtoDTO.setQtdEstoque(resultSet.getInt("produto_qtdEstoque"));
        produtoDTO.setAtivo(resultSet.getInt("produto_ativo"));
        produtoDTO.setDataCadastro(resultSet.getTimestamp("produto_dataCadastro"));
        produtoDTO.setDataAlteracao(resultSet.getTimestamp("produto_dataAlteracao"));
        pedidoBusca.setProdutoDTO(produtoDTO);
        pedidoBusca.setId(resultSet.getInt("pedido_id"));
        pedidoBusca.setQuantidade(resultSet.getInt("pedido_quantidade"));
        pedidoBusca.setValorUnitario(resultSet.getFloat("pedido_valorUnitario"));
        pedidoBusca.setValorTotal(resultSet.getFloat("pedido_valorTotal"));
        return pedidoBusca;
    }
}
