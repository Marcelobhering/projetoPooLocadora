package repositorys;

import models.Carro;
import models.Model;
import models.Moto;
import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Utilitario;
import models.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO extends DAO {

	private static PessoaDAO uniqueInstance;

	public PessoaDAO() {
	}

	@Override
	public synchronized PessoaDAO getInstancia() {
		if (uniqueInstance == null) {
			uniqueInstance = new PessoaDAO();
			uniqueInstance.setRepositorio(new ArrayList());
		}
		return this.uniqueInstance;
	}

	@Override
	public Pessoa consultar(Integer id) {
		Model model = super.consultar(id);
		if (model == null) {
			System.out.println("\nCliente nao encontrado!");
		}
		return (Pessoa) model;
	}

	public Pessoa consultar(String cpfCnpj) {
		Pessoa pessoaEncontrada = null;

		for (Model model : getRepository()) {

			Pessoa pessoa = (Pessoa) model;
			// instanceof PARA VERIFICAR QUAL A INSTANCIA DA CLASSE PAI
			if (pessoa instanceof PessoaFisica) {
				PessoaFisica pf = (PessoaFisica) pessoa;
				if (pf.getCpf().equalsIgnoreCase(cpfCnpj)) {
					pessoaEncontrada = pf;

					break;
				}

			} else if (pessoa instanceof PessoaJuridica) {
				PessoaJuridica pj = (PessoaJuridica) pessoa;
				if (pj.getCnpj().equalsIgnoreCase(cpfCnpj)) {
					pessoaEncontrada = pj;
					break;
				}
			}

		}

		return pessoaEncontrada;
	}

	public Pessoa getPessoa(Integer id, String tipo) {
		for (Model model : getRepository()) {

			Pessoa pessoa = (Pessoa) model;

			if (tipo.equals("F") && pessoa instanceof PessoaFisica) {
				if (pessoa.getId() == id) {
					return pessoa;
				}

			} else if (tipo.equals("J") && pessoa instanceof PessoaJuridica) {
				if (pessoa.getId() == id) {
					return pessoa;
				}

			}

		}
		return null;
	}

	@Override
	public void listar(String tipo) {
		System.out.println("\nLista de Clientes cadastrados");
		for (Model model : getRepository()) {

			Pessoa pessoa = (Pessoa) model;

			if (tipo.equals("F") && pessoa instanceof PessoaFisica) {
				System.out.println("--------------------------------------");
				System.out.println("Id do cliente : " + pessoa.getId());
				System.out.println("Nome do cliente : " + pessoa.getNome());
				System.out.println("--------------------------------------");
			} else if (tipo.equals("J") && pessoa instanceof PessoaJuridica) {
				System.out.println("--------------------------------------");
				System.out.println("Id do cliente : " + pessoa.getId());
				System.out.println("Nome do cliente : " + pessoa.getNome());
				System.out.println("--------------------------------------");
			}

		}

	}

}
