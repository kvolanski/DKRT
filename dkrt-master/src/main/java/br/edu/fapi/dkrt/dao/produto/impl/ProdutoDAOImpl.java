package br.edu.fapi.dkrt.dao.produto.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.sql.*;

public class ProdutoDAOImpl implements ProdutoDAO {

    @Override
    public int createProduto(ProdutoDTO produtoDTO) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtos (produto_nome, produto_descricao, " +
                    "produto_precoVenda, produto_precoCusto, produto_qtdEstoque, produto_ativo, produto_dataCadastroProduto)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, produtoDTO.getNome());
            preparedStatement.setString(2, produtoDTO.getDescricao());
            preparedStatement.setDouble(3, produtoDTO.getPrecoVenda());
            preparedStatement.setDouble(4, produtoDTO.getPrecoCusto());
            preparedStatement.setInt(5, produtoDTO.getQtdEstoque());
            preparedStatement.setInt(6, produtoDTO.getAtivo());
            preparedStatement.setDate(7, new java.sql.Date(produtoDTO.getDataCadastroProduto().getTime()));

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int idProduto = resultSet.getInt(1);
                return idProduto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
