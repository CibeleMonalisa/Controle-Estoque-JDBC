package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Conexao;
import modelo.Marca;


public class MarcaDao {
	private Connection conexao;
	private PreparedStatement stmt;
	//aqui nós estabelecemos que essa classe crie uma conexão com o banco de dados
	public MarcaDao() {
		this.conexao = new Conexao().getConexao();
	}
	//nesse método, iremos passar valores na classe main que irá ser inserida 
	//no banco de dados
	public Marca salvar(Marca marca) {
		String sql= "INSERT INTO marca (nome) VALUES (?)";
		try {
			stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, marca.getNome());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			marca.setId(rs.getInt(1));
			stmt.close();
	        return marca;
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
}
	//este método recebe como parâmetro uma string que vem do método main e retorna uma lista
	//com as informações de uma marca conforme ela ter o mesmo nome procurado
	public ArrayList<Marca> pesquisarNome(String a) {
		String sql = "SELECT * FROM marca where nome=(?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, a);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Marca> lista = new ArrayList<>();
			//esse while vai percorrer o resultset(rs) que é a lista de marcas, procurando
			//chegar se tem mais uma marca ou se chegou ao fim da lista, adicionando todas
			//as marcas encontradas pelo comando select
			while(rs.next()) { 
				Marca b=new Marca (rs.getInt("id"),rs.getString("nome"));
				lista.add(b);
			}
			stmt.close();
			return lista;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	//esse método pega uma marca passada e pesquisa pelo seu nome, retornando
	//as informações por meio de um arraylist
	public ArrayList<Marca> pesquisarNome(Marca a) {
		String sql = "SELECT * FROM marca where nome=(?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, a.getNome());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Marca> lista = new ArrayList<>();
	//este while faz a mesma coisa que o anterior, percorrendo o resultset e salvando as
	//marcas dentro do arraylist
			while(rs.next()) { 
				Marca b=new Marca (rs.getInt("id"),rs.getString("nome"));
				lista.add(b);
			}
			stmt.close();
			return lista;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	public Marca pesquisarId(int u) {
		String sql = "SELECT * FROM marca WHERE id =" +u;
		try {
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Marca b = new Marca(rs.getInt("id"),rs.getString("nome"));
			stmt.close();
			return b;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public Marca pesquisarId(Marca r) {
		return pesquisarId(r.getId());
	}
}
