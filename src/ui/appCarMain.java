package ui;

import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import fachada.AppCarFacade;

public class appCarMain {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		int opcao;

		do {

			System.out.println();
			System.out.println("  Locadora de veiculos GMA\n");

			System.out.println("Digite a opcao desejada\n");
			System.out.println("------------------------");
			System.out.println("         Cadastros   ");
			System.out.println("(1)- Cadastrar cliente:");
			System.out.println("(2)- Cadastrar veiculo:");
			System.out.println("(3)- Cadastrar reserva:");

			System.out.println("------------------------");
			System.out.println("         Consultas  ");

			System.out.println("(4)- Consultar cliente:");
			System.out.println("(5)- Consultar veiculo:");
			System.out.println("(6)- Consultar reserva:");

			System.out.println("------------------------");
			System.out.println("        Exclusoes  ");

			System.out.println("(7)- Excluir cliente:");
			System.out.println("(8)- Excluir veiculo:");
			System.out.println("(9)- Excluir reserva:");

			System.out.println("------------------------");
			System.out.println("          Edicoes  ");

			System.out.println("(10)- Editar cliente:");
			System.out.println("(11)- Editar veiculo:");
			System.out.println("(12)- Editar reserva:");

			System.out.println("------------------------");
			System.out.println("         Fatura  ");

			System.out.println("(13)- Emitir fatura:");

			System.out.println("------------------------");

			System.out.println("(14)- Sair:");

			System.out.println("------------------------");

			opcao = sc.nextInt();
			

			switch (opcao) {

			// CADASTRO CLIENTE
			case 1: {

				AppCarFacade.getInstancia().cadastrarCliente();

			}
				break;

			// CADASTRO VEICULO
			case 2: {

				AppCarFacade.getInstancia().cadastrarVeiculo();

			}
				break;

			// CADASTRO RESERVA
			case 3: {

				AppCarFacade.getInstancia().cadastrarReserva();

			}
				break;
			// CONSULTAR CLIENTE
			case 4: {

				AppCarFacade.getInstancia().consultarCliente();

			}
				break;

			// CONSULTAR VEICULO
			case 5: {

				AppCarFacade.getInstancia().consultarVeiculo();

			}
				break;

			// CONSULTAR RESERVA
			case 6: {

				AppCarFacade.getInstancia().consultarReserva();

			}
				break;

			// EXCLUIR CLIENTE

			case 7: {

				AppCarFacade.getInstancia().excluirCliente();

			}
				break;

			// EXCLUIR VEICULO
			case 8: {

				AppCarFacade.getInstancia().excluirVeiculo();

			}
				break;
			// EXCLUIR RESERVA
			case 9: {

				AppCarFacade.getInstancia().excluirReserva();

			}
				break;
			// EDITAR CLIENTE
			case 10: {

				AppCarFacade.getInstancia().editarCliente();

			}

				break;
			// EDITAR VEICULO
			case 11: {

				AppCarFacade.getInstancia().editarVeiculo();

			}

				break;
			// EDITAR RESERVA
			case 12: {

				AppCarFacade.getInstancia().editarReserva();

			}

				break;

			// EMITIR FATURA
			case 13: {

				AppCarFacade.getInstancia().emitirFatura();

			}
				break;
			// SAIR DO SISTEMA
			case 14: {
				System.out.println("Obrigado por utilizar nossos servicos!!!");
			}
				break;
			}
		} while (opcao != 14);

	}
}
