package br.edu.fapi.dkrt.usuario.impl;

import br.edu.fapi.dkrt.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.usuario.UsuarioDTO;
import br.edu.fapi.dkrt.usuario.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public int createUsuario(UsuarioDTO usuario) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuarios (login, senha)" +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e){
            System.out.println("Falha na conexao");
        }
        return 0;
    }
}
