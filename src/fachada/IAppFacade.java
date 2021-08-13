package fachada;

import java.text.ParseException;

public interface IAppFacade {

	void cadastrarCliente();

	void cadastrarVeiculo();

	void cadastrarReserva();

	void consultarCliente();

	void consultarVeiculo();

	void consultarReserva();

	void excluirCliente();

	void excluirVeiculo();

	void excluirReserva();

	void editarCliente();

	void editarVeiculo();

	void editarReserva();

	void emitirFatura();

}
