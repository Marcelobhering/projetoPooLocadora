package controllers;

import models.Veiculo;

public interface IVeiculoController {

	int criarVeiculo(Veiculo veiculo);

	Veiculo consultarVeiculo(Integer id);

	Veiculo consultarVeiculo(String placa);

	Veiculo atualizarVeiculo(Veiculo veiculoParaAtualizar);

	void deletar(Integer id);

	void listar(String tipo);

	Veiculo getVeiculo(Integer id, String tipo);

	void listarVeiculoDisponivel(String tipo);

}
