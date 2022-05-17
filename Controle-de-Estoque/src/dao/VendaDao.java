package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import modelo.Conexao;
import modelo.Produto;
import modelo.Venda;

public class VendaDao { 
	private Connection conexao;
	private PreparedStatement stmt;
	public VendaDao() {
		this.conexao = new Conexao().getConexao();
	}
	public Venda salvar(Venda venda) {
	    String sql = "INSERT INTO venda (total, idCliente) VALUES (?, ?)";
	    try {
	        stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt.setDouble(1, venda.getTotal());
	        stmt.setInt(2, venda.getCliente().getId());
	        stmt.execute();
	        ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        venda.setId(rs.getInt(1));
	        salvarItens(venda);
	        stmt.close();
	        return venda;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	public void salvarItens(Venda venda) {
	    String sql = "INSERT INTO itensVenda (idProduto, idVenda) VALUES (?, ?)";
	    try {
	        stmt = conexao.prepareStatement(sql);
	        for(Produto produto:venda.getItens()) {
	        	stmt.setInt(1, produto.getId());
	        	stmt.setInt(2, venda.getId());
	        	stmt.execute();
	        }
	        stmt.close();
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
