package utils;

import java.util.Scanner;

public class CpfUtils {

	public static String formatCpf(String cpf) {
		Scanner sc = new Scanner(System.in);
		String cpfFormatado = null;
		int countponto;
		int counthifen;

		countponto = cpf.length() - cpf.replace(".", "").length();
		counthifen = cpf.length() - cpf.replace("-", "").length();

		if (cpf.length() < 11 || cpf.length() > 15) {
			System.out.println("CPF no formato incorreto, Insira no formato xxx.xxx.xxx-xx ");
			cpfFormatado = formatCpf(sc.nextLine());

		} else if (cpf.length() == 15 && countponto != 2 && counthifen != 1) {
			System.out.println("CPF no formato incorreto, Insira no formato xxx.xxx.xxx-xx ");
			cpfFormatado = formatCpf(sc.nextLine());

		} else if (cpf.length() == 11 && counthifen <= 0 && countponto <= 0) {

			cpfFormatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-"
					+ cpf.substring(9, 11);
			return cpfFormatado;

		} else if (cpf.length() == 15 && countponto == 2 && counthifen == 1) {
			return cpf;
		}
		return cpfFormatado;

	}

}
