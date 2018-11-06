package br.edu.fapi.dkrt.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionProvider {
//      Base original
//    public static String url = "jdbc:mysql://localhost:3306/dkrt_erp";

//      Base teste
    public static String url = "jdbc:mysql://localhost:3306/dkrt_erp_teste2";
    public static String usuario = "root";
    public static String senha = "";

    public static Connection abrirConexao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, usuario, senha);
    }


}
