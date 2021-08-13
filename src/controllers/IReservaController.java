package controllers;

import models.Reserva;

public interface IReservaController {

	 int criarReserva(Reserva reserva);

	 Reserva consultarReserva(Integer id);

	 Reserva atualizarReserva(Reserva reservaParaAtualizar);

	 void deletar(Integer id);

	 void salvarReserva();

	 void listar(String tipo);

}
