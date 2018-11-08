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
                    "produto_precoVenda, produto_precoCusto, produto_qtdEstoque, produto_ativo, produto_dataCadastro)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, produtoDTO.getNome());
            preparedStatement.setString(2, produtoDTO.getDescricao());
            preparedStatement.setDouble(3, produtoDTO.getPrecoVenda());
            preparedStatement.setDouble(4, produtoDTO.getPrecoCusto());
            preparedStatement.setInt(5, produtoDTO.getQtdEstoque());
            preparedStatement.setInt(6, produtoDTO.getAtivo());
            preparedStatement.setDate(7, new java.sql.Date(produtoDTO.getDataCadastro().getTime()));

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

    @Override
    public boolean verificaSeProdutoExiste(String nome) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produtos WHERE produto_nome = ?");

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
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
    public ProdutoDTO buscarProdutoPorNome(String nome) {
        ProdutoDTO produtoBusca = new ProdutoDTO();
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produtos WHERE produto_nome = ?");

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                produtoBusca.setId(resultSet.getInt("produto_id"));
                produtoBusca.setNome(resultSet.getString("produto_nome"));
                produtoBusca.setDescricao(resultSet.getString("produto_descricao"));
                produtoBusca.setPrecoVenda(resultSet.getFloat("produto_precoVenda"));
                produtoBusca.setPrecoCusto(resultSet.getFloat("produto_precoCusto"));
                produtoBusca.setQtdEstoque(resultSet.getInt("produto_qtdEstoque"));
                produtoBusca.setAtivo(resultSet.getInt("produto_ativo"));
                produtoBusca.setDataCadastro(resultSet.getDate("produto_dataCadastro"));
                produtoBusca.setDataAlteracao(resultSet.getDate("produto_dataAlteracao"));
            }
            return produtoBusca;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produtoBusca;
    }

    @Override
    public boolean atualizaProdutoCad(ProdutoDTO produtoDTO) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET produto_nome = ?, produto_descricao = ?, " +
                    "produto_qtdEstoque = ?, produto_precoVenda = ?, produto_precoCusto = ?, produto_dataAlteracao = ? WHERE produto_id = ?");

            preparedStatement.setString(1, produtoDTO.getNome());
            preparedStatement.setString(2, produtoDTO.getDescricao());
            preparedStatement.setInt(3, produtoDTO.getQtdEstoque());
            preparedStatement.setDouble(4, produtoDTO.getPrecoVenda());
            preparedStatement.setDouble(5, produtoDTO.getPrecoCusto());
            preparedStatement.setDate(6, new java.sql.Date(produtoDTO.getDataAlteracao().getTime()));
            preparedStatement.setInt(7, produtoDTO.getId());

            boolean sucesso = preparedStatement.execute();

            if (!sucesso){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
