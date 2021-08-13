package models;

public abstract class Pessoa extends Model {

	private String nome;

	private String endereco;

	protected boolean ativo = false;

	public Pessoa() {
	}

	public Pessoa(Integer id, String nome, String endereco) {
		super(id);
		this.nome = nome;
		this.endereco = endereco;
	}

	public Pessoa(Integer id, String nome, String endereco, boolean ativo) {
		super(id);
		this.nome = nome;
		this.endereco = endereco;
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public boolean setAtivo(boolean ativo) {
		return this.ativo = setAtivo(ativo);
	}
//

	@Override
	public String toString() {
		return "\nNome = " + nome + "\nEndereco = " + endereco;
	}

}
