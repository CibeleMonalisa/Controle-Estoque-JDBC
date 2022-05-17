package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Conexao;
import modelo.Pessoa;
import modelo.PessoaFisica;
import modelo.PessoaJuridica;

;

public class PessoaDao {
	private Connection conexao;
	private PreparedStatement stmt;
	public PessoaDao() {
		this.conexao = new Conexao().getConexao();
	}
	
	public Pessoa salvar(Pessoa pessoa) {
			String sql = "INSERT INTO cliente (nome, endereco, cpfCnpj, fisicoJuridico) VALUES (?, ?, ?, ?)";
			try {
				stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				if(pessoa instanceof PessoaJuridica) {
					stmt.setString(1, pessoa.getNome());
					stmt.setString(2, pessoa.getEndereco());
					stmt.setString(3, ((PessoaJuridica)pessoa).getCnpj());
					stmt.setString(4, "j");
				}else
					if(pessoa instanceof PessoaFisica) {
						stmt.setString(1, pessoa.getNome());
						stmt.setString(2, pessoa.getEndereco());
						stmt.setString(3, ((PessoaFisica)pessoa).getCpf());
						stmt.setString(4, "f");
					}
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			pessoa.setId(rs.getInt(1));
			stmt.close();
			return pessoa;
			} catch (Exception e) {
		        throw new RuntimeException(e);
			}
	}
			
			public ArrayList<Pessoa> pesquisarNome(String a) {
				String sql = "SELECT * FROM cliente where nome=(?)";
				try {
					stmt = conexao.prepareStatement(sql);
					stmt.setString(1, a);
					ResultSet rs = stmt.executeQuery();
					ArrayList<Pessoa> lista = new ArrayList<>();
					while(rs.next()) { 
						if(rs.getString(5).equals("j")) {
							Pessoa b= new PessoaJuridica (rs.getInt("id"),rs.getString("nome"), rs.getString("endereco"), rs.getString("cpfCnpj"));
							lista.add(b);
						}else
							if(rs.getString(5).equals("f")) {
								Pessoa b= new PessoaFisica (rs.getInt("id"),rs.getString("nome"), rs.getString("endereco"), rs.getString("cpfCnpj"));
								lista.add(b);
							}
					}
					stmt.close();
					return lista;
				}catch (Exception e) {
					throw new RuntimeException(e);
				}	
			}
			public ArrayList<Pessoa> pesquisarNome(Pessoa pessoa) {
				String sql = "SELECT * FROM cliente where nome=(?)";
				try {
					stmt = conexao.prepareStatement(sql);
					stmt.setString(1, pessoa.getNome());
					ResultSet rs = stmt.executeQuery();
					ArrayList<Pessoa> lista = new ArrayList<>();
					while(rs.next()) { 
						if(rs.getString(5).equals("j")) {
							Pessoa b= new PessoaJuridica (rs.getInt("id"),rs.getString("nome"), rs.getString("endereco"), rs.getString("cpfCnpj"));
							lista.add(b);
						}else
							if(rs.getString(5).equals("f")) {
								Pessoa b= new PessoaFisica (rs.getInt("id"),rs.getString("nome"), rs.getString("endereco"), rs.getString("cpfCnpj"));
								lista.add(b);
							}
					}
					stmt.close();
					return lista;
				}catch (Exception e) {
					throw new RuntimeException(e);
				}		
	
			}
			public Pessoa pesquisarId(int u) {
				String sql = "SELECT * FROM cliente WHERE id =(?)" ;
				try {
					stmt = conexao.prepareStatement(sql);
					stmt.setInt(1, u);
					ResultSet rs = stmt.executeQuery();
					Pessoa p = null;
					rs.next();
					if(rs.getString(5).equals("j")) {
						Pessoa b= new PessoaJuridica (rs.getInt("id"),rs.getString("nome"), rs.getString("endereco"), rs.getString("cpfCnpj"));
						p= b;
					}else
						if(rs.getString(5).equals("f")) {
							Pessoa b= new PessoaFisica (rs.getInt("id"),rs.getString("nome"), rs.getString("endereco"), rs.getString("cpfCnpj"));
						p= b;
						}
					stmt.close();
					return p;
				}catch (Exception e) {
					throw new RuntimeException(e);
				}	
			}
			public Pessoa pesquisarId(Pessoa r) {
				return pesquisarId(r.getId());
			}	
}