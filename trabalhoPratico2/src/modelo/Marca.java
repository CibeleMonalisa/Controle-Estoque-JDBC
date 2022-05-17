package modelo;

public class Marca {
	private int id;
	private String nome;
	//método construtor para evitar que as informações de marca sejam deixadas em branco
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
	//esse método evita que seja retornado o endereço de memória e sim as informações em texto
	@Override
	public String toString() {
		String dados;
		dados="Id:" + this.getId() + "\nNome:" + this.getNome();
		return dados;
	}
	
	
}