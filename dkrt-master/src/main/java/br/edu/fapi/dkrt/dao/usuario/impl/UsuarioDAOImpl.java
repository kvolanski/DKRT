package br.edu.fapi.dkrt.dao.usuario.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.usuario.UsuarioDAO;
import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public int createUsuario(UsuarioDTO usuario) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuario (usuario_email, usuario_senha)" +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            System.out.println("Falha na conexao");
        }
        return 0;
    }

    @Override
    public UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }
}
