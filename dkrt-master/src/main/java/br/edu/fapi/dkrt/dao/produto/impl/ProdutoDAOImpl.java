package br.edu.fapi.dkrt.dao.produto.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;

import java.sql.*;

public class ProdutoDAOImpl implements ProdutoDAO {
    @Override
    public int createProduto(ProdutoDTO produtoDTO) {

        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO produto (produto_nome,produto_descricao,produto_preco,produto_quantidade,produto_dataCadastroProduto,produto_dataAlteracaoProduto)" +
                            " VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, produtoDTO.getNome());
            preparedStatement.setString(2, produtoDTO.getDescricao());
            preparedStatement.setDouble(3, produtoDTO.getPreco());
            preparedStatement.setInt(4, produtoDTO.getQuantidade());
            preparedStatement.setDate(5, (Date) produtoDTO.getDataCadastroProduto());
            preparedStatement.setDate(6, (Date) produtoDTO.getDataAlteracaoProduto());


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
