package models;

public class PessoaJuridica extends Pessoa {

	private String cnpj;

	private boolean situacaoCadastral;

	public PessoaJuridica() {
	}

	public PessoaJuridica(String cnpj, boolean situacaoCadastral) {
		super();
		this.cnpj = cnpj;
		this.situacaoCadastral = situacaoCadastral;
	}

	public PessoaJuridica(Integer id, String nome, String endereco, boolean ativo, String cnpj) {
		super(id, nome, endereco, ativo);
		this.cnpj = cnpj;

	}

	public PessoaJuridica(Integer id, String nome, String endereco, String cnpj) {
		super(id, nome, endereco);
		this.cnpj = cnpj;

	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public boolean isSituacaoCadastral() {
		return situacaoCadastral;
	}

	public void setSituacaoCadastral(boolean situacaoCadastral) {
		this.situacaoCadastral = situacaoCadastral;
	}

	@Override
	public String toString() {
		return "Pessoa Juridica:\n" + "\n\nAtivo:" + setAtivo(ativo) + "\nRazao Social = " + super.getNome()
				+ "\nEndere�o = " + super.getEndereco() + "\nCnpj=" + cnpj + "\nSituaco cadastral = "
				+ situacaoCadastral ;
	}

	@Override
	public boolean setAtivo(boolean ativo) {

		if (situacaoCadastral == true) {
			super.ativo = true;

		} else {
			super.ativo = false;

		}
		return this.isAtivo();
	}

}
