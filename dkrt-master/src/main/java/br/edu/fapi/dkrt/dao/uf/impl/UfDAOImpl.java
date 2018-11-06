package br.edu.fapi.dkrt.dao.uf.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.uf.UfDAO;
import br.edu.fapi.dkrt.model.uf.UfDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UfDAOImpl implements UfDAO {

    @Override
    public List<UfDTO> buscarListaUfs() {
        List<UfDTO> listaUfs = null;

        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            listaUfs = new ArrayList<UfDTO>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ufs ORDER BY uf_sigla");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UfDTO ufDTO = new UfDTO();
                ufDTO.setId(resultSet.getInt("uf_id"));
                ufDTO.setSigla(resultSet.getString("uf_sigla"));
                ufDTO.setNome(resultSet.getString("uf_nome"));
                listaUfs.add(ufDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaUfs;
    }
}
