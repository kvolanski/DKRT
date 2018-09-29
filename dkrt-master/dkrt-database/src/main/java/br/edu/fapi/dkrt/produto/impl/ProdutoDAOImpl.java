package br.edu.fapi.dkrt.produto.impl;

import br.edu.fapi.dkrt.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.produto.ProdutoDAO;
import br.edu.fapi.dkrt.produto.ProdutoDTO;

import java.sql.*;

public class ProdutoDAOImpl implements ProdutoDAO {
    @Override
    public int createProduto(ProdutoDTO produtoDTO) {

        try(Connection connection = MySqlConnectionProvider.abrirConexao()) {

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO produto (nome,descricao,preco,quantidade,dataCadastroProduto,dataAlteracaoProduto)" +
                            " VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1,produtoDTO.getNome());
            preparedStatement.setString(2,produtoDTO.getDescricao());
            preparedStatement.setDouble(3,produtoDTO.getPreco());
            preparedStatement.setInt(4,produtoDTO.getQuantidade());
            preparedStatement.setDate(5,(Date) produtoDTO.getDataCadastroProduto());
            preparedStatement.setDate(6,(Date) produtoDTO.getDataAlteracaoProduto());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
