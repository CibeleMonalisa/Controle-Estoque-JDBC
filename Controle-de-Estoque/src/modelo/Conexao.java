package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	public static final String SERVIDOR = "jdbc:mysql://localhost/trab2?useTimezone=true&serverTimezone=UTC";
	public static final String USUARIO = "root";
	public static final String SENHA = "123456";
	
	public Connection getConexao() {
		try {
				return DriverManager.getConnection(SERVIDOR, USUARIO, SENHA);
				
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}