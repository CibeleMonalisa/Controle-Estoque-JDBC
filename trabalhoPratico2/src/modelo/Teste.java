package modelo;

import java.util.ArrayList;
import dao.MarcaDao;
import dao.PessoaDao;
import dao.ProdutoDao;
import dao.VendaDao;

public class Teste {
	public static void main(String[] args) {
		
		//comando para salvar Marca no banco de dados
		Marca a = new Marca("Colgate");
		Marca b = new Marca("Elefante");
		
		//padrão projeto DAO (Data Access Object) para a classe PessoaDao
		PessoaDao pessoaDao= new PessoaDao();
		
		//padrão projeto DAO (Data Access Object) para a classe PessoaDao
		VendaDao vendaDao= new VendaDao();
		
		//padrão projeto DAO (Data Access Object) para a classe MarcaDao
		MarcaDao marcaDao = new MarcaDao();
		
		//padrão projeto DAO (Data Access Object) para a classe ProdutoDao
		ProdutoDao produtoDao = new ProdutoDao();
//---------------------------------------------------------------------------------------
		//salvar pessoas
		
		//Físicas
		PessoaFisica pf1=new PessoaFisica("Maria", "Rua 3", "33333333333");
		PessoaFisica pf2=new PessoaFisica("Luiza", "Rua 4", "33222222222");
		pf1= (PessoaFisica)pessoaDao.salvar(pf1);
		pf2= (PessoaFisica)pessoaDao.salvar(pf2);
		
		//Jurídicas
		PessoaJuridica pj1=new PessoaJuridica("Móveis", "Rua 5", "55555555/5");
		PessoaJuridica pj2=new PessoaJuridica("Escovas", "Rua 24", "44444444/2");
		pj1= (PessoaJuridica)pessoaDao.salvar(pj1);
		pj2= (PessoaJuridica)pessoaDao.salvar(pj2);
		
//-----------------------------------------------------------------------------------------
		//vai salvar no banco os dados as marcas acima:
		System.out.println("\nPesquisa de nome da marca tendo como parâmetro um id:");
		marcaDao.salvar(a);
		a=marcaDao.pesquisarId(1);
		marcaDao.salvar(b);
		b=marcaDao.pesquisarId(2);
		
		
		//pesquisa as marcas pelo parametro string passado no método main
		System.out.println("\n--------------------------------------------------");
		System.out.println("\nPesquisa de nome da marca tendo como parâmetro uma string:");
		ArrayList<Marca> pesquisa=marcaDao.pesquisarNome("Elefante");
		for (Marca marca : pesquisa) {
			System.out.println(marca);
		}
		
		//pesquisa as marcas por nome e retorna em forma de lista;
		System.out.println("\n--------------------------------------------------");
		System.out.println("\nPesquisa de nome da marca tendo como parâmetro uma string:");
		ArrayList<Marca> pesquisar = marcaDao.pesquisarNome(a);
		for (Marca marca : pesquisar) {
			System.out.println(marca);
		}


		//pesquisa as marcas com a marca como parâmetro e retorna a marca
		System.out.println("\nPesquisa de nome da marca tendo como parâmetro uma marca:");
		System.out.println(marcaDao.pesquisarId(a));
		System.out.println(marcaDao.pesquisarId(b));
		
		//comando para salvar Produto no banco de dados
		Produto p1 = new Produto ("Pasta de dentes", 2.5, a);
		Produto p2 = new Produto ("Molho de tomate", 5.5, b);
			
		//vai salvar no banco os dados as produtos acima:
		produtoDao.salvar(p1);
		produtoDao.salvar(p2);
				
		//pesquisa os produtos pelo parametro string passado no método main
		System.out.println("\nPesquisa de nome do produto tendo como parâmetro uma string:");
		ArrayList<Produto> x= produtoDao.pesquisarNome("Molho de tomate");
				for (Produto produto : x) {
					System.out.println(produto);
				}
				
		//pesquisa os produtos por nome e retorna em forma de lista, onde é usado como parametro um tipo marca;
		System.out.println("\n--------------------------------------------------");
		System.out.println("Pesquisa de nome do produto tendo como parâmetro um produto:");
		ArrayList<Produto> t= produtoDao.pesquisarNome(p1);
				for (Produto produto : t) {
					System.out.println(produto);
				}
				
		//pesquisa as marcas por id e retorna a marca
		System.out.println("\nPesquisa os produtos tendo como parâmetro o id");
		produtoDao.salvar(p1);
		p1=produtoDao.pesquisarId(1);
		produtoDao.salvar(p2);
		p2=produtoDao.pesquisarId(2);
				
		//pesquisa as marcas com a marca como parâmetro e retorna a marca
		produtoDao.pesquisarId(p1);
		produtoDao.pesquisarId(p2);
		
		System.out.println("\n Pesquisa de nome do cliente tendo como parâmetro uma string:");
		ArrayList<Pessoa>pessoa= pessoaDao.pesquisarNome("Maria");
		for(Pessoa p: pessoa) {
			System.out.println(p.imprimirDados());
		}
		
		System.out.println("\n--------------------------------------------------");
		System.out.println("\nPesquisa pelo nome do cliente usando uma pessoa");
		ArrayList<Pessoa> pessoas = pessoaDao.pesquisarNome(pj2);
		for(Pessoa p: pessoas) {
			System.out.println(p.imprimirDados());
		}
		
		System.out.println("\n--------------------------------------------------");
		System.out.println("\nPesquisa por um cliente usando um id");
		pf2= (PessoaFisica) pessoaDao.pesquisarId(2);
		System.out.println(pf2.imprimirDados());
//------------------------------------------------------------------------------
		//registrar venda
		ArrayList<Produto> lista = new ArrayList<Produto>();
		lista.add(p1);
		lista.add(p2);
		Venda v1=new Venda(pf1, lista);
		v1= vendaDao.salvar(v1);
		System.out.println(v1.imprimirDados());
		
	}
	
}
