package modelo;

public class Produto {
	private int id;
	private String nome;
	private double preco;	
	private Marca marca;
	//m�todo construtor para evitar que seja deixado em branco
	public Produto(String nome, double preco, Marca marca) {
		this.nome = nome;
		this.preco = preco;
		this.marca = marca;
	}
	public Produto(int id, String nome, double preco, Marca marca) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.marca = marca;
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


	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}


	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}


	//esse m�todo evita que seja retornado o endere�o de mem�ria e sim as informa��es em texto
	@Override
	public String toString() {
		String dados;
		dados="Id:" + this.getId() + "\nNome:" + this.getNome() + "\nPre�o:" + this.getPreco() + "\nMarca:" + this.getMarca();
		return dados;
	}
	
}
