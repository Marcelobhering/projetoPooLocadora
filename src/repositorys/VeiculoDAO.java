package repositorys;

import java.util.ArrayList;
import java.util.List;

import models.Carro;
import models.Model;
import models.Moto;
import models.Pessoa;
import models.Utilitario;
import models.Veiculo;

public class VeiculoDAO extends DAO {

	private static VeiculoDAO uniqueInstance;

	public VeiculoDAO() {

	}

	@Override
	public synchronized VeiculoDAO getInstancia() {
		if (uniqueInstance == null) {
			uniqueInstance = new VeiculoDAO();
			uniqueInstance.setRepositorio(new ArrayList());
		}
		return this.uniqueInstance;
	}

	@Override
	public Veiculo consultar(Integer id) {
		Model model = super.consultar(id);
		if (model == null) {
			System.out.println("\nVeiculo nao encontrado!");
		}
		return (Veiculo) model;
	}

	public Veiculo consultar(String placa) {
		Veiculo veiculoEncontrado = null;

		for (Model model : getRepository()) {

			Veiculo veiculo = (Veiculo) model;
			// instanceof PARA VERIFICAR QUAL A INSTANCIA DA CLASSE PAI
			if (veiculo instanceof Carro) {
				Carro car = (Carro) veiculo;
				if (car.getPlaca().equalsIgnoreCase(placa)) {
					veiculoEncontrado = car;

					break;
				}

			} else if (veiculo instanceof Moto) {
				Moto moto = (Moto) veiculo;
				if (moto.getPlaca().equalsIgnoreCase(placa)) {
					veiculoEncontrado = moto;

					break;
				}
			} else if (veiculo instanceof Utilitario) {
				Utilitario utilitario = (Utilitario) veiculo;
				if (utilitario.getPlaca().equalsIgnoreCase(placa)) {
					veiculoEncontrado = utilitario;

					break;
				}
			}

		}

		return veiculoEncontrado;
	}

// @Override
	public void listar(String tipo) {
		System.out.println("\nLista de Veiculos cadastrados");
		for (Model model : getRepository()) {

			Veiculo veiculo = (Veiculo) model;

			if (tipo.equals("M") && veiculo instanceof Moto) {
				System.out.println("--------------------------------------");
				System.out.println("Id do Veiculo : " + veiculo.getId());
				System.out.println("Id do Placa : " + veiculo.getPlaca());
				System.out.println("--------------------------------------");
			} else if (tipo.equals("C") && veiculo instanceof Carro) {
				System.out.println("--------------------------------------");
				System.out.println("Id do Veiculo : " + veiculo.getId());
				System.out.println("Id do Placa : " + veiculo.getPlaca());
				System.out.println("--------------------------------------");
			} else if (tipo.equals("U") && veiculo instanceof Utilitario) {
				System.out.println("--------------------------------------");
				System.out.println("Id do Veiculo : " + veiculo.getId());
				System.out.println("Id do Placa : " + veiculo.getPlaca());
				System.out.println("--------------------------------------");
			}

		}
	}

	public void listarVeiculoDisponivel(String tipo) {
		System.out.println("\nVeiculos Disponiveis");
		for (Model model : getRepository()) {

			Veiculo veiculo = (Veiculo) model;

			if (tipo.equals("M") && veiculo instanceof Moto && veiculo.isAlugado() != true) {

				System.out.println("--------------------------------------");
				System.out.println("Id do Veiculo : " + veiculo.getId());
				System.out.println("Id do Placa : " + veiculo.getPlaca());
				System.out.println("--------------------------------------");

			} else if (tipo.equals("C") && veiculo instanceof Carro && veiculo.isAlugado() != true) {

				System.out.println("--------------------------------------");
				System.out.println("Id do Veiculo : " + veiculo.getId());
				System.out.println("Id do Placa : " + veiculo.getPlaca());
				System.out.println("--------------------------------------");

			} else if (tipo.equals("U") && veiculo instanceof Utilitario && veiculo.isAlugado() != true) {

				System.out.println("--------------------------------------");
				System.out.println("Id do Veiculo : " + veiculo.getId());
				System.out.println("Id do Placa : " + veiculo.getPlaca());
				System.out.println("--------------------------------------");

			}

		}
	}

// VEICULO ESPECIFICO
	public Veiculo getVeiculo(Integer id, String tipo) {

		for (Model model : getRepository()) {

			Veiculo veiculo = (Veiculo) model;
			// instanceof PARA VERIFICAR QUAL A INSTANCIA DA CLASSE PAI
			if (tipo.equals("C") && veiculo instanceof Carro) {
				if (veiculo.getId() == id) {

					return veiculo;
				}

			} else if (tipo.equals("M") && veiculo instanceof Moto) {
				if (veiculo.getId() == id) {

					return veiculo;
				}

			} else if (tipo.equals("U") && veiculo instanceof Utilitario) {
				if (veiculo.getId() == id) {

					return veiculo;
				}

			}

		}

		return null;

	}

}
