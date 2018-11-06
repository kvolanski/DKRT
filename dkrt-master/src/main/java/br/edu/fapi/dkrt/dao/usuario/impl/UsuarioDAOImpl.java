package br.edu.fapi.dkrt.dao.usuario.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.usuario.UsuarioDAO;
import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean validarUsuario(UsuarioDTO usuarioDTO) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE usuario_login = ? AND usuario_senha = ?");

            preparedStatement.setString(1, usuarioDTO.getEmail());
            preparedStatement.setString(2, usuarioDTO.getSenha());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioDTO.setId(resultSet.getInt("usuario_id"));
                usuarioDTO.setEmail(resultSet.getString("usuario_login"));
                usuarioDTO.setSenha(resultSet.getString("usuario_senha"));
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
    public int createUsuario(UsuarioDTO usuario) {
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuarios (usuario_email, usuario_senha)" +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            System.out.println("Falha na conexao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public UsuarioDTO buscarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioBusca = new UsuarioDTO();
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE usuario_id = ? ");

            preparedStatement.setInt(1, usuarioDTO.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioBusca.setId(resultSet.getInt("usuario_id"));
                usuarioBusca.setEmail(resultSet.getString("usuario_login"));
                usuarioBusca.setSenha(resultSet.getString("usuario_senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarioBusca;
    }
}
