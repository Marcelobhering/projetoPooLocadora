package utils;

import java.util.Scanner;

public class CnpjUtils {

	public static String formatCnpj(String cnpj) {
		Scanner sc = new Scanner(System.in);
		String cnpjFormatado = null;
		int countponto;
		int counthifen;
		int countbarra;

		countponto = cnpj.length() - cnpj.replace(".", "").length();
		counthifen = cnpj.length() - cnpj.replace("-", "").length();
		countbarra = cnpj.length() - cnpj.replace("/", "").length();

		if (cnpj.length() < 14 || cnpj.length() > 18) {
			System.out.println("CNPJ no formato incorreto, Insira no formato xx.xxx.xxx/xxxx-xx ");
			cnpjFormatado = formatCnpj(sc.nextLine());

		} else if ((cnpj.length() == 14 || cnpj.length() > 14)
				&& (counthifen > 0 || countponto > 0 || countbarra > 0)) {
			System.out.println("CNPJ no formato incorreto, Insira no formato xx.xxx.xxx/xxxx-xx ");
			cnpjFormatado = formatCnpj(sc.nextLine());

		} else if (cnpj.length() == 14 && counthifen == 0 && countponto == 0 && countbarra == 0) {

			cnpjFormatado = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/"
					+ cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
			return cnpjFormatado;

		} else if (cnpj.length() == 18 && countponto != 2 && counthifen != 1 && countbarra != 1) {
			System.out.println("CNPJ no formato incorreto, Insira no formato xx.xxx.xxx/xxxx-xx ");
			cnpjFormatado = formatCnpj(sc.nextLine());
		}

		else if (cnpj.length() == 18 && countponto == 2 && counthifen == 1 && countbarra == 1) {
			return cnpj;
		}
		return cnpjFormatado;

	}

}
