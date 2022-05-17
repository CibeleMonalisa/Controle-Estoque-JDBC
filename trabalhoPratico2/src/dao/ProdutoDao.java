package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Conexao;
import modelo.Produto;
public class ProdutoDao {
	private Connection conexao;
	private MarcaDao m = new MarcaDao();
	PreparedStatement stmt;
	//aqui nós estabelecemos que essa classe crie uma conexão com o banco de dados
	public ProdutoDao() {
		this.conexao = new Conexao().getConexao();
	}
	//nesse método, iremos passar valores na classe main que irá ser inserida 
		//no banco de dados. Caso seja retornado true, os dados foram inseridos
	public Produto salvar(Produto produto) {
		String sql= "INSERT INTO produto (nome, preco, marca) VALUES (?, ?, ?)";
		try {
			
			stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getMarca().getId());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        produto.setId(rs.getInt(1));
	        stmt.close();
	        return produto;
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
}
	//este método recebe como parâmetro uma string que vem do método main e retorna uma lista
	//com as informações de um produto já inserido conforme ele ter o mesmo nome procurado
	public ArrayList<Produto> pesquisarNome(String a) {
		String sql = "SELECT * FROM produto where nome=(?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, a);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Produto> lista = new ArrayList<>();
			//esse while vai percorrer o resultset(rs) que é a lista de produtos, procurando
			//chegar se tem mais um produto a ser colocado na lista ou se chegou ao fim, adicionando todos
			//os produtos encontrados pelo comando select
			while(rs.next()) { 
				Produto b= new Produto (rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), m.pesquisarId(rs.getInt("marca")));
				lista.add(b);
			}
			stmt.close();
			return lista;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	//esse método pega um produto passado e pesquisa pelo seu nome, retornando
	//as informações (nome, preço e marca) por meio de um arraylist
	public ArrayList<Produto> pesquisarNome(Produto a) {
		String sql = "SELECT * FROM produto where nome=(?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, a.getNome());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Produto> lista = new ArrayList<>();
			while(rs.next()) { 
				Produto b= new Produto (rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), m.pesquisarId(rs.getInt("marca")));
				lista.add(b);
			}
			stmt.close();
			return lista;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	//este método pesquisa o produto por um número inteiro informado, que seria um id
	//retornando o nome do produto correspondente ao id informado
	public Produto pesquisarId(int a) {
		String sql = "SELECT * FROM produto where id= (?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, a);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Produto b= new Produto (rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), m.pesquisarId(rs.getInt("marca")));
			stmt.close();
			return b;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	//este método pesquisa recebe um produto informado e salvo no banco, 
	//retornando o nome do produto correspondente ao id do produto informado, seguindo os métodos construtores já definidos
	public Produto pesquisarId(Produto h) {
		return this.pesquisarId(h.getId());
	}
}