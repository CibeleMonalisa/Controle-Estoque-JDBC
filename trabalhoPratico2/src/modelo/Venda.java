package modelo;

import java.util.ArrayList;

public class Venda {
	private int id;
	private ArrayList<Produto> itens;
	private double total;
	private Pessoa cliente;
	
	public Venda(Pessoa pessoa, ArrayList<Produto> produtos) {
		this.cliente=pessoa;
		this.itens=produtos;
		this.total=valorTotal();
	}
	public Venda(int id, Pessoa pessoa, ArrayList<Produto> produtos) {
		this.id=id;
		this.cliente=pessoa;
		this.itens=produtos;
		this.total=valorTotal();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Produto> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	private double valorTotal() {
		for(Produto produto:itens) {
			this.total += produto.getPreco();
		}
		return this.total;
	}
	public String imprimirDados() {
		String texto;
		texto="Nome do cliente:" + this.cliente.getNome()+"\nValor da venda:" + this.getTotal() + "\n Quantidade de itens:" + itens.size();
		return texto;
	
	}
}
