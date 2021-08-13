package repositorys;

import java.util.ArrayList;

import models.Model;
import models.Reserva;

public class ReservaDAO extends DAO {

	private static ReservaDAO uniqueInstance;

	public ReservaDAO() {

	}

	@Override
	public synchronized ReservaDAO getInstancia() {
		if (uniqueInstance == null) {
			uniqueInstance = new ReservaDAO();
			uniqueInstance.setRepositorio(new ArrayList());
		}
		return this.uniqueInstance;
	}

	@Override
	public Reserva consultar(Integer id) {
		Model model = super.consultar(id);
		if (model == null) {
			System.out.println("\nReserva nao encontrada!");
		}
		return (Reserva) model;
	}

	@Override
	public void listar(String r) {
		System.out.println("\nLista de Reservas cadastradas");
		for (Model model : getRepository()) {

			Reserva reserva = (Reserva) model;

			System.out.println("--------------------------------------");
			System.out.println("Reserva | id :" + reserva.getId());
			System.out.println("Cliente | nome :" + reserva.getCliente().getNome());
			System.out.println("Veiculo | placa :" + reserva.getVeiculo().getPlaca());
			System.out.println("--------------------------------------");

		}

	}

}
