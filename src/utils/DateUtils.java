package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import models.Fatura;
import models.Reserva;

public class DateUtils {

	/** Metodo resposavel por retornar a diferente de dias entre datas */

	public int diasEntreDatas(Date dataInicio, Date dataFim) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(dataInicio);

		Calendar dataFinal = Calendar.getInstance();
		dataFinal.setTime(dataFim);

		int diferenca = dataFinal.get(Calendar.YEAR) - dataInicial.get(Calendar.YEAR);

		if (dataFinal.get(Calendar.MONTH) < dataInicial.get(Calendar.MONTH)) {
			diferenca--;
		} else {
			if (dataFinal.get(Calendar.MONTH) == dataInicial.get(Calendar.MONTH)
					&& dataFinal.get(Calendar.DAY_OF_MONTH) < dataInicial.get(Calendar.DAY_OF_MONTH)) {
				diferenca--;
			}
		}

		return diferenca;
	}

	public static double quantDias(Reserva reserva) {

		long tempo1 = reserva.getInicio().getTime();
		long tempo2 = reserva.getFim().getTime();
		double horas = (double) (tempo2 - tempo1) / 1000 / 60 / 60; // converter miliseg para segundos(/1000) de seg.
																	// minutos(/60)e seg. para horas (/60)

		double dias;

		if (horas <= 12.0) {
			dias = Math.ceil(horas);
			return dias;

		} else {
			dias = Math.ceil(horas / 24);
		

		}
		return dias;

	}

	public static String formatDate(String data) {
		Scanner sc = new Scanner(System.in);
		String dataFormatada = null;
		int count;
		count = data.length() - data.replace("/", "").length();
		if (data.length() < 8 || data.length() > 10) {
			System.out.println("\nData no formato incorreto, Insira a data no formato DD/MM/YYYY.");
			dataFormatada = formatDate(sc.nextLine());

		} else if (data.length() == 10 && count != 2) {
			System.out.println("Data no formato incorreto, Insira a data no formato DD/MM/YYYY.");
			dataFormatada = formatDate(sc.nextLine());

		} else if (data.length() == 8 && count <= 0) {
			dataFormatada = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, 8);
			return dataFormatada;

		} else if (data.length() == 10 && count == 2) {
			return data;
		}
		return dataFormatada;

	}

}

