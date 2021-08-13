package controllers;

import java.awt.List;

import models.Carro;
import models.Model;
import models.Pessoa;
import models.Veiculo;
import repositorys.VeiculoDAO;

public class VeiculoController implements IVeiculoController {

	private VeiculoDAO veiculoDAO;

	public VeiculoController() {
		this.veiculoDAO = new VeiculoDAO().getInstancia();

	}

	public int criarVeiculo(Veiculo veiculo) {

		veiculo.setId(null);
		int id = this.veiculoDAO.inserir(veiculo);
		return id;
	}

	public Veiculo consultarVeiculo(Integer id) {
		Veiculo veiculo = this.veiculoDAO.consultar(id);
		return veiculo;
	}

	public void listar(String tipo) {

		veiculoDAO.listar(tipo);

	}

	public void listarVeiculoDisponivel(String tipo) {

		veiculoDAO.listarVeiculoDisponivel(tipo);

	}

	public Veiculo getVeiculo(Integer id, String tipo) {
		Veiculo vc = veiculoDAO.getVeiculo(id, tipo);

		return vc;

	}

//casting (Ve�culo)
	public Veiculo consultarVeiculo(String placa) {
		Veiculo veiculo = (Veiculo) this.veiculoDAO.consultar(placa);
		return veiculo;
	}

	public Veiculo atualizarVeiculo(Veiculo veiculoParaAtualizar) {
		Veiculo veiculoSalvo = consultarVeiculo(veiculoParaAtualizar.getId());

		veiculoSalvo.setKilometragem(veiculoParaAtualizar.getKilometragem());
		veiculoSalvo.setMarca(veiculoParaAtualizar.getMarca());
		veiculoSalvo.setAlugado(veiculoParaAtualizar.isAlugado());
		veiculoSalvo.setModelo(veiculoParaAtualizar.getModelo());

		if (veiculoSalvo instanceof Carro) {
			Carro c = (Carro) veiculoSalvo;
			Carro carroPraAtualizar = (Carro) veiculoParaAtualizar;

			c.setMarca(carroPraAtualizar.getMarca());
			c.setAlugado(carroPraAtualizar.isAlugado());

		}

		Model model = this.veiculoDAO.atualizar(veiculoSalvo);
		return (Veiculo) model;
	}

	public void deletar(Integer id) {
		this.veiculoDAO.deletar(id);
	}

}
