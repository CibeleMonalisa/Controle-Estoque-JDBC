package modelo;

public class PessoaJuridica extends Pessoa {
	private String cnpj;
	
	public PessoaJuridica(String nome, String endereco, String cnpj) {
		this.setNome(nome);
		this.setEndereco(endereco);
		this.cnpj= cnpj;
	}
	public PessoaJuridica(int id, String nome, String endereco, String cnpj) {
		this.setId(id);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.cnpj= cnpj;
	}
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	@Override
	public String imprimirDados() {
		String texto;
		texto="Nome:" + this.getNome()+"\n Endereço:" + this.getEndereco() + "\n CNPJ:" + this.getCnpj();
		return texto;
	}
	@Override
	public String toString() {
		String texto;
		texto="Id:" + this.getId() + "\n Nome:" + this.getNome()+"\n Endereço:" + this.getEndereco() + "\n CPNJ:" + this.getCnpj();
		return texto;
	}
	
}
