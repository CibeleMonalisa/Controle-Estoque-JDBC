package modelo;

public class Marca {
	private int id;
	private String nome;
	//m�todo construtor para evitar que as informa��es de marca sejam deixadas em branco
	public Marca( String nome) {
		this.nome = nome;
	}
	public Marca(int id, String nome) {
		this.id = id;
		this.nome = nome;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	//esse m�todo evita que seja retornado o endere�o de mem�ria e sim as informa��es em texto
	@Override
	public String toString() {
		String dados;
		dados="Id:" + this.getId() + "\nNome:" + this.getNome();
		return dados;
	}
	
	
}