package controllers;

import models.Model;
import models.Pessoa;
import models.Reserva;
import models.Veiculo;
import repositorys.ReservaDAO;

public class ReservaController implements IReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {

		
		this.reservaDAO = new ReservaDAO().getInstancia();
	}

	public int criarReserva(Reserva reserva) {

		reserva.setId(null);

		int id = this.reservaDAO.inserir(reserva);

		return id;

	}

	public Reserva consultarReserva(Integer id) {
		Reserva reserva = this.reservaDAO.consultar(id);
		return reserva;
	}

	public void listar() {
		String r = null;
		reservaDAO.listar(r);
	}

	public Reserva atualizarReserva(Reserva reservaParaAtualizar) {
		Reserva reservaSalva = consultarReserva(reservaParaAtualizar.getId());

		reservaSalva.setCliente(reservaParaAtualizar.getCliente());
		reservaSalva.setVeiculo(reservaParaAtualizar.getVeiculo());
		reservaSalva.setInicio(reservaParaAtualizar.getInicio());
		reservaSalva.setFim(reservaParaAtualizar.getFim());

		// if (reservaSalva instanceof Reserva) { // Reserva sendo uma classe concreta
		// nao precis usar instanceof

		Reserva r = (Reserva) reservaSalva;
		Reserva rPraAtualizar = (Reserva) reservaParaAtualizar;

		r.setCliente(rPraAtualizar.getCliente());
		r.setVeiculo(rPraAtualizar.getVeiculo());
		r.setInicio(rPraAtualizar.getInicio());
		r.setFim(rPraAtualizar.getFim());
		// }

		Model model = this.reservaDAO.atualizar(reservaSalva);
		return (Reserva) model;
	}

	public void deletar(Integer id) {
		this.reservaDAO.deletar(id);
	}

	public void salvarReserva() {

	}

	@Override
	public void listar(String tipo) {
		String r = null;
		reservaDAO.listar(r);

	}
}
