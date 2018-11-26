package br.edu.fapi.dkrt.dao.produto.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            produtoBusca = fillProduto(resultSet);

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
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
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

            if (!sucesso) {
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
    public ProdutoDTO buscaProdutoPorId(int id) {
        ProdutoDTO produtoBusca = new ProdutoDTO();
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produtos WHERE produto_id = ?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            produtoBusca = fillProduto(resultSet);

            return produtoBusca;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produtoBusca;
    }

    @Override
    public List<ProdutoDTO> listarProdutos() {
        List<ProdutoDTO> listaProdutos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProdutoDTO produtoDTO = new ProdutoDTO();
                produtoDTO.setId(resultSet.getInt("produto_id"));
                produtoDTO.setNome(resultSet.getString("produto_nome"));
                produtoDTO.setDescricao(resultSet.getString("produto_descricao"));
                produtoDTO.setPrecoVenda(resultSet.getFloat("produto_precoVenda"));
                produtoDTO.setPrecoCusto(resultSet.getFloat("produto_precoCusto"));
                produtoDTO.setQtdEstoque(resultSet.getInt("produto_qtdEstoque"));
                produtoDTO.setAtivo(resultSet.getInt("produto_ativo"));
                produtoDTO.setDataCadastro(resultSet.getDate("produto_dataCadastro"));
                produtoDTO.setDataAlteracao(resultSet.getDate("produto_dataAlteracao"));
                listaProdutos.add(produtoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    @Override
    public boolean diminuirEstoque(ProdutoDTO produtoDTO) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET produto_qtdEstoque = ? WHERE produto_id = ?");

            preparedStatement.setInt(1, produtoDTO.getQtdEstoque());
            preparedStatement.setInt(2, produtoDTO.getId());

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
    public boolean aumentarEstoque(ProdutoDTO produtoDTO) {
        String sql = "UPDATE produtos SET produto_qtdEstoque = ? WHERE produto_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, produtoDTO.getQtdEstoque());
            preparedStatement.setInt(2, produtoDTO.getId());

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

    private ProdutoDTO fillProduto(ResultSet resultSet) throws SQLException {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        if (resultSet.next()) {
            produtoDTO.setId(resultSet.getInt("produto_id"));
            produtoDTO.setNome(resultSet.getString("produto_nome"));
            produtoDTO.setDescricao(resultSet.getString("produto_descricao"));
            produtoDTO.setPrecoVenda(resultSet.getFloat("produto_precoVenda"));
            produtoDTO.setPrecoCusto(resultSet.getFloat("produto_precoCusto"));
            produtoDTO.setQtdEstoque(resultSet.getInt("produto_qtdEstoque"));
            produtoDTO.setAtivo(resultSet.getInt("produto_ativo"));
            produtoDTO.setDataCadastro(resultSet.getDate("produto_dataCadastro"));
            produtoDTO.setDataAlteracao(resultSet.getDate("produto_dataAlteracao"));
        }
        return produtoDTO;
    }
}
