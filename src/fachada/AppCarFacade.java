package fachada;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controllers.ClienteController;
import controllers.ReservaController;
import controllers.VeiculoController;
import models.Carro;
import models.Moto;
import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Reserva;
import models.Utilitario;
import models.Veiculo;
import models.enums.TipoCarroEnum;
import models.enums.TipoCorEnum;
import models.enums.TipoMotoEnum;
import models.enums.TipoUtilitarioEnum;
import models.service.ServicoAluguel;
import models.service.TaxaBrasil;
import repositorys.ReservaDAO;
import utils.CnpjUtils;
import utils.CpfUtils;
import utils.DateUtils;

public class AppCarFacade implements IAppFacade {

	Scanner sc = new Scanner(System.in);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	ClienteController clienteController = new ClienteController();
	VeiculoController veiculoController = new VeiculoController();
	ReservaController reservaController = new ReservaController();

	// SINGLETON
	private static AppCarFacade uniqueInstance;

	private AppCarFacade() {

	}

	public static synchronized AppCarFacade getInstancia() {
		if (uniqueInstance == null) {
			uniqueInstance = new AppCarFacade();

		}
		return uniqueInstance;

	}

	// FIM SINGLETON

	public void cadastrarCliente() {

		int subcliente;

		System.out.println("\n(1) - Cadastrar pessoa fisica: ");
		System.out.println("(2) - Cadastrar pessoa juridica: ");
		System.out.print("(3) - Voltar:");
		subcliente = sc.nextInt();
		{
			switch (subcliente) {
			case 1: {

				// CADASTRO PESSOA FISICA
				System.out.print("\nQuantos Clientes deseja cadastrar ? ");
				int n = sc.nextInt();
				sc.nextLine();
				for (int i = 0; i < n; i++) {
					String dataNasc = null;
					try {
						System.out.print("\nDigite a data de nascimento: ");
						dataNasc = sc.nextLine();
						dataNasc = DateUtils.formatDate(dataNasc);
						Date dataNascimento = sdf.parse(dataNasc);

						// System.out.println(sdf.format(dataNascimento));

						PessoaFisica pf = new PessoaFisica(dataNascimento);

						if (pf.getIdade() < 18) {

							System.out.println("\nCliente inapto para cadastro, idade inferior a 18 anos!");

						} else {

							System.out.printf("Digite o nome do %d cliente: ", (i + 1));
							String nome = sc.nextLine();
							pf.setNome(nome);
							System.out.print("Digite o endereco: ");
							String endereco = sc.nextLine();
							pf.setEndereco(endereco);
							System.out.print("Digite o numero do cpf: ");
							String cpf = sc.nextLine();
							pf.setCpf(cpf);
							System.out.print("Digite o numero da cnh: ");
							String cnh = sc.nextLine();
							pf.setCnh(cnh);

							int idClienteSalvo = clienteController.criarCliente(pf);
							System.out.println(
									"\ncliente criado com sucesso - " + pf + " \nid do cliente = " + idClienteSalvo);

						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						System.out.println("Digite uma data no formato correto!");
					}
				}
			}
				break;
			case 2: {
				// CADASTRO pessoa juridica
				System.out.print("\nDigite a quantidade de clientes que deseja realizar o cadastro:");
				int n = sc.nextInt();

				for (int i = 0; i < n; i++) {
					sc.nextLine();

					System.out.print("\nDigite o cnpj da empresa:");

					String cnpj = sc.nextLine();

					System.out.println("\nRealize a consulta do cnpj no site da Receita Federal:");
					System.out.print("\nPara cnpj regularizado digite (1) nao regularizado digite (0):");

					int op = sc.nextInt();
					boolean situacaoCadastral = false;
					if (op == 1) {
						situacaoCadastral = true;

					} else {
						situacaoCadastral = false;
					}

					PessoaJuridica pj = new PessoaJuridica(cnpj, situacaoCadastral);

					if (op == 0) {

						System.out.println("Cliente  inapto para cadastro, cnpj invalido!");
					} else {
						sc.nextLine();

						System.out.printf("\nDigite a razao social da %dª empresa:", (i + 1));
						String nome = sc.nextLine();
						pj.setNome(nome);
						System.out.print("Digite o endereco: ");
						String endereco = sc.nextLine();
						pj.setEndereco(endereco);

						int idClienteSalvo = clienteController.criarCliente(pj);

						System.out.println(
								"\nCliente criado com sucesso - " + pj + "\nid do cliente = " + idClienteSalvo);

					}
				}
			}
				break;

			}
		}
	}

	public void cadastrarVeiculo() {

		int subVeiculo;
		System.out.println("\n(1) - Cadastrar carro: ");
		System.out.println("(2) - Cadastrar utilitario: ");
		System.out.println("(3) - Cadastrar moto: ");
		System.out.print("(4) - Voltar: ");
		subVeiculo = sc.nextInt();
		{
			switch (subVeiculo) {
			case 1: {
				// CADASTRO carro
				System.out.print("\nDigite a quantidade de carros que deseja realizar o cadastro: ");
				int n = sc.nextInt();

				for (int i = 0; i < n; i++) {
					sc.nextLine();

					System.out.printf("\nDigite os dados do %dº carro\n", (i + 1));
					System.out.print("\nDigite a placa: ");
					String placa = sc.nextLine();
					System.out.print("\nDigite a marca: ");
					String marca = sc.nextLine();
					System.out.print("\nDigite o modelo: ");
					String modelo = sc.nextLine();
					System.out.print("\nDigite a cor: ");

					String corStr = sc.next();
					corStr = corStr.toUpperCase();
					TipoCorEnum cor = TipoCorEnum.valueOf(corStr);
					sc.nextLine();
					System.out.print("\nDigite a kilometragem: ");
					long kilometragem = sc.nextLong();
					System.out.print("\nDigite a quantidade de passageiros: ");
					int quantidadePassageiros = sc.nextInt();
					System.out.print("\nDigite o tipo do carro: ");

					String tipoCar = sc.next();
					tipoCar = tipoCar.toUpperCase();
					TipoCarroEnum tipo = TipoCarroEnum.valueOf(tipoCar);

					Carro car = new Carro(i, placa, marca, modelo, cor, kilometragem, quantidadePassageiros, tipo);

					int idVeiculoSalvo = veiculoController.criarVeiculo(car);

					System.out.println("\nVeiculo registrado com sucesso: \n" + "\nDados cadastrados: \n" + car);
				}
			}
				break;

			case 2: {
				// CADASTRO Utilitario
				System.out.print("\nDigite a quantidade de utilitario que deseja realizar o cadastro: ");
				int n = sc.nextInt();

				for (int i = 0; i < n; i++) {
					sc.nextLine();

					System.out.printf("\nDigite os dados do %dº utilitario\n", (i + 1));

					System.out.print("\nDigite a placa: ");
					String placa = sc.nextLine();
					// String placa = "aaaa100" + i;

					System.out.print("\nDigite a marca: ");
					String marca = sc.nextLine();
					// String marca = "bbbb";

					System.out.print("\nDigite o modelo: ");
					String modelo = sc.nextLine();
					// String modelo = "cccc";

					System.out.print("\nDigite a cor: ");

					String corStr = sc.next();
					// String corStr = "preto";
					corStr = corStr.toUpperCase();
					TipoCorEnum cor = TipoCorEnum.valueOf(corStr);
					// sc.nextLine();

					System.out.print("\nDigite a kilometragem: ");
					long kilometragem = sc.nextLong();
					// long kilometragem = 444444;

					System.out.print("\nDigite a capacidade de carga: ");
					int capacidadeCarga = sc.nextInt();
					// int capacidadeCarga = 4;

					System.out.print("\nDigite o tipo do carro: ");

					String tipoUti = sc.next();
					// String tipoUti = "furgao";
					tipoUti = tipoUti.toUpperCase();
					TipoUtilitarioEnum tipo = TipoUtilitarioEnum.valueOf(tipoUti);

					Utilitario utilit = new Utilitario(i, placa, marca, modelo, cor, kilometragem, capacidadeCarga,
							tipo);

					int idVeiculoSalvo = veiculoController.criarVeiculo(utilit);

					System.out.println("\nVeiculo registrado com sucesso: \n" + "\nDados cadastrados: \n" + utilit);
				}
			}
				break;
			case 3: {
				// CADASTRO moto
				System.out.print("\nDigite a quantidade de motos que deseja realizar o cadastro:");
				int n = sc.nextInt();

				for (int i = 0; i < n; i++) {
					sc.nextLine();

					System.out.printf("\nDigite os dados da %dª moto\n", (i + 1));
					System.out.print("\nDigite a placa: ");
					String placa = sc.nextLine();
					System.out.print("\nDigite a marca: ");
					String marca = sc.nextLine();
					System.out.print("\nDigite o modelo:");
					String modelo = sc.nextLine();
					System.out.print("\nDigite a cor: ");

					String corStr = sc.next();
					corStr = corStr.toUpperCase();
					TipoCorEnum cor = TipoCorEnum.valueOf(corStr);

					System.out.print("\nDigite a kilometragem: ");
					long kilometragem = sc.nextLong();
					System.out.print("\nDigite a quantidade de passageiros: ");
					int quantidadePassageiros = sc.nextInt();
					// System.out.print("Digite a quantidade de passageiros:");
					// BigDecimal seguroAdicional = sc.nextBigDecimal();
					System.out.print("\nDigite o tipo da moto: ");

					String tipoMoto = sc.next();
					tipoMoto = tipoMoto.toUpperCase();
					TipoMotoEnum tipo = TipoMotoEnum.valueOf(tipoMoto);

					Moto moto = new Moto(i, placa, modelo, modelo, cor, kilometragem, quantidadePassageiros, tipo);

					int idVeiculoSalvo = veiculoController.criarVeiculo(moto);

					System.out.println("\nVeiculo registrado com sucesso: \n" + "\nDatos cadastrados: \n" + moto);

				}
			}
				break;
			}
		}
	}

	public void cadastrarReserva() {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		int novo = 0;
		do {
			System.out.println("\nCadastro de reservas:");
			System.out.println();

			System.out.println("Reserva de carro digite ( C )");
			System.out.println("Reserva de utilitario digite ( U )");
			System.out.print("Reserva de moto digite ( M )");
			String tipoV = sc.next();
			tipoV = tipoV.toUpperCase();

			veiculoController.listarVeiculoDisponivel(tipoV);

			System.out.println("\nDigite os dados da reserva");
			sc.nextLine();

			System.out.println("\nVeiculo");

			System.out.println("\nDigite o id do Veiculo:");
			int idVeiculo = sc.nextInt();
			Veiculo vc = veiculoController.getVeiculo(idVeiculo, tipoV);

			String modelo = vc.getModelo();
			String placa = vc.getPlaca();

			System.out.print("\nDigite o valor da diaria: ");
			Double valorDiaria = sc.nextDouble();
			System.out.print("Digite o valor da hora: ");
			Double valorHora = sc.nextDouble();

			Veiculo veiculo = null;

			if (tipoV.equals("C")) {
				Veiculo carro = veiculoController.consultarVeiculo(idVeiculo);
				carro.setValorDiaria(valorDiaria);
				carro.setValorHora(valorHora);
				carro.setModelo(modelo);
				carro.setPlaca(placa);
				carro.setAlugado(true);
				veiculo = carro;
				System.out.println("\nVeiculo:" + "\nDados cadastrados do carro \n" + carro);

			} else if (tipoV.equals("U")) {

				Veiculo utilitario = veiculoController.consultarVeiculo(idVeiculo);
				utilitario.setValorDiaria(valorDiaria);
				utilitario.setValorHora(valorHora);
				utilitario.setModelo(modelo);
				utilitario.setPlaca(placa);
				utilitario.setAlugado(true);
				veiculo = utilitario;

				System.out.println("\nVeiculo:" + "\nDados cadastrados do utilitario \n" + utilitario);

			} else if (tipoV.equals("M")) {

				Veiculo moto = veiculoController.consultarVeiculo(idVeiculo);
				moto.setValorDiaria(valorDiaria);
				moto.setValorHora(valorHora);
				moto.setModelo(modelo);
				moto.setPlaca(placa);
				moto.setAlugado(true);
				veiculo = moto;
				System.out.println("\nVeiculo:" + "\nDados cadastrados da moto \n" + moto);

			}

			System.out.println("\nQual o tipo de cliente ?");
			System.out.println("\nCliente pessoa fisica digite ( F )");
			System.out.print("Cliente pessoa juridica digite ( J )");
			String tipoP = sc.next();
			tipoP = tipoP.toUpperCase();

			clienteController.listar(tipoP);
			System.out.print("\nDigite o id do Cliente:");
			int id = sc.nextInt();
			Pessoa ps = clienteController.getPessoa(id, tipoP);
			String nome = ps.getNome();
			String endereco = ps.getEndereco();

			Pessoa pessoa = null;

			if (tipoP.equals("F")) {
				PessoaFisica pf = (PessoaFisica) clienteController.consultarCliente(id);
				pf.setNome(nome);
				pf.setEndereco(endereco);
				pessoa = pf;
				System.out.println("\nCliente:" + "\nDados cadastrados da " + pf);

			} else if (tipoP.equals("J")) {
				Pessoa pj = (PessoaJuridica) clienteController.consultarCliente(id);
				pj.setNome(nome);
				pj.setEndereco(endereco);
				pessoa = pj;
				System.out.println("\nCliente:" + "\nDados cadastrados da " + pj);

			}

			try {

				sc.nextLine();
				System.out.print("Digite data do check in (dd/MM/yyyy):");
				String dataHoraIn = sc.nextLine();

				System.out.print("Digite a hora do check in (HH:ss):");
				dataHoraIn = dataHoraIn + " " + sc.nextLine();
				Date inicio = sdf1.parse(dataHoraIn);

				System.out.print("Digite data do check out (dd/MM/yyyy):");
				String dataHoraOut = sc.nextLine();

				System.out.print("Digite a hora do check out (HH:ss):");
				dataHoraOut = dataHoraOut + " " + sc.nextLine();
				Date fim = sdf1.parse(dataHoraOut);

				Reserva res = new Reserva(inicio, fim, pessoa, veiculo);

				reservaController.criarReserva(res);
				System.out.println("\nReserva criada com sucesso!");
				System.out.println("\nDados da reserva:\n" + res);

				int dias = (int) DateUtils.quantDias(res);
				System.out.print("Quantidade de dias de reserva  = " + dias);
				System.out.println();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (novo == 1);

	}

	public void consultarCliente() {
		// CONSULTAR CLIENTE

		System.out.print("\nDigite a quantidade de cliente que deseja consultar no cadastro:");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			sc.nextLine();

			System.out.print("\nDigite (1) consulta por cpf/cnpj ou (2) id do cliente:");
			int op = sc.nextInt();

			if (op == 1) {
				System.out.println("\nDigite o cpf/cnpj do cliente:");
				String cpfCnpj = sc.next();
				sc.nextLine();

				Pessoa p = clienteController.consultarCliente(cpfCnpj);

				System.out.println("\nCliente:" + "\nDados cadastrados - " + p);

			} else {
				System.out.println("\nDigite o id do cliente:");
				int id = sc.nextInt();
				sc.nextLine();

				Pessoa p = clienteController.consultarCliente(id);
				System.out.println("\nCliente:" + "\nDados cadastrados - ");
			}

		}
	}

	public void consultarVeiculo() {

		// CONSULTAR VEICULO

		System.out.print("\nDigite a quantidade de veiculos que deseja consultar no cadastro:");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			sc.nextLine();

			System.out.print("\nDigite (1) consulta por placa ou (2) id do veiculo:");
			int op = sc.nextInt();

			if (op == 1) {
				System.out.println("\nDigite a placa do veiculo:");
				String placa = sc.next();
				sc.nextLine();

				Veiculo v = veiculoController.consultarVeiculo(placa);
				System.out.println("\nVeiculo:" + "\nDados cadastrados - " + v);

			} else {

				System.out.println("\nDigite o id do veiculo:");
				int id = sc.nextInt();
				sc.nextLine();
				Veiculo v = veiculoController.consultarVeiculo(id);
				System.out.println("\nVeiculo:" + "\nDados cadastrados - " + v);

			}

		}
	}

	public void consultarReserva() {

		System.out.print("\nDigite a quantidade de reservas que deseja consultar no cadastro:");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {

			reservaController.listar();
			System.out.print("\nDigite o id da reserva:");
			int id = sc.nextInt();

			Reserva res = reservaController.consultarReserva(id);

			System.out.println("\nReserva:" + "\nDatos cadastrados - " + res + "\nid da reserva " + id);

		}

	}

	public void excluirCliente() {

		String tipoP;
		System.out.print("\nDigite (F) - Excluir cliente Pessoa Fisica: ");
		System.out.print("\nDigite (J) - Excluir cliente Pessoa Juridica: ");
		System.out.print("\nDigite (3) - Voltar: ");
		tipoP = sc.nextLine();
		tipoP = tipoP.toUpperCase();
		clienteController.listar(tipoP);
		{
			if (tipoP.equals("F")) {
				// excluir CLIENTE PESSOA fisica

				System.out.print("\nDigite a quantidade de cliente que deseja excluir do cadastro:");
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {

					System.out.print("\nDigite o id do cliente:");
					int id = sc.nextInt();

					Pessoa pF = clienteController.consultarCliente(id);

					if (pF == null) {

						System.out.println("\nPessoa fisica nï¿½o encontrada");

					} else {

						clienteController.deletar(id);

						System.out.println("\nCliente excluido com sucesso! " + "\nCom o id = " + id);
					}
				}
			}

			if (tipoP.equals("J")) {
				// excluir CLIENTE PESSOA JURUDICA

				System.out.print("\nDigite a quantidade de cliente que deseja excluir do cadastro:");
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {

					System.out.print("\nDigite o id do cliente:");
					int id = sc.nextInt();

					Pessoa pJ = clienteController.consultarCliente(id);

					if (pJ == null) {

						System.out.println("\nPessoa juridica nao encontrada");

					} else {
						clienteController.deletar(id);

						System.out.println("\nCliente excluido com sucesso! " + "\nCom o id = " + id);

					}
				}
			}

		}
	}

	public void excluirVeiculo() {

		String tipoV;
		System.out.println("\nDigite (C) - Excluir carro: ");
		System.out.println("\nDigite (U) - Excluir utilitario: ");
		System.out.println("\nDigite (M) - Excluir moto: ");
		System.out.print("\nDigite (4) - Voltar: \n");
		tipoV = sc.nextLine();
		tipoV = tipoV.toUpperCase();
		veiculoController.listar(tipoV);

		{
			if (tipoV.equals("C")) {
				// excluir CARRO

				System.out.print("\nDigite a quantidade de carros que deseja excluir do cadastro:");
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {

					System.out.print("\nDigite o id do veiculo:");
					int id = sc.nextInt();

					Veiculo carro = veiculoController.consultarVeiculo(id);

					if (carro == null) {
						System.out.println("\nCarro nao encontrado");
					} else {
						veiculoController.deletar(id);

						System.out.println("\nVeiculo excluido com sucesso! " + "\nCom o id = " + id);

					}
				}
			} else if (tipoV.equals("U")) {

				// excluir utilitario

				System.out.print("\nDigite a quantidade de utilitarios que deseja excluir do cadastro:");
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {

					System.out.print("\nDigite o id do utilitario:");
					int id = sc.nextInt();

					Veiculo utilitario = veiculoController.consultarVeiculo(id);

					if (utilitario == null) {
						System.out.println("\nVeiculo nao encontrado");
					} else {
						veiculoController.deletar(id);

						System.out.println("\nVeiculo excluido com sucesso! " + "\nCom o id = " + id);

					}
				}
			} else if (tipoV.equals("M")) {

				// excluir moto

				System.out.print("\nDigite a quantidade de motos que deseja excluir do cadastro:");
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {

					System.out.print("\nDigite o id da Veiculo:");
					int id = sc.nextInt();

					Veiculo moto = veiculoController.consultarVeiculo(id);
					if (moto == null) {
						System.out.println("\nMoto nao encontrada");
					} else {
						veiculoController.deletar(id);

						System.out.println("\nVeiculo excluido com sucesso!" + "\nCom o id = " + id);

					}
				}
			}

		}
	}

	public void excluirReserva() {
		System.out.print("\nDigite a quantidade de reservas que deseja excluir do cadastro:");
		int n = sc.nextInt();
		reservaController.listar();
		for (int i = 0; i < n; i++) {

			System.out.print("\nDigite o id da reserva: ");
			int id = sc.nextInt();
			Reserva reserva = reservaController.consultarReserva(id);
			if (reserva == null) {

				System.out.println("\nReserva nao encontrada");

			} else {
				reservaController.deletar(id);
				System.out.println("\nReserva excluida com sucesso: " + "\nCom o id = " + id);

			}

		}

	}

	public void editarCliente() {
		int novo;

		String tipoP;
		System.out.print("\nQual tipo de cliente ?");
		System.out.print("\nDigite (F) - Editar fessoa fisica: ");
		System.out.print("\nDigite (J) - Editar cliente pessoa juridica: ");
		tipoP = sc.nextLine();
		tipoP = tipoP.toUpperCase();
		clienteController.listar(tipoP);

		do {

			System.out.print("\nDigite o id do cliente:");
			int id = sc.nextInt();
			Pessoa cliente = clienteController.consultarCliente(id);

			if (cliente != null) {

				if (cliente instanceof PessoaFisica) {
					sc.nextLine();
					System.out.print("\nDigite o novo endereco:");
					String endereco = sc.nextLine();
					cliente.setEndereco(endereco);
					System.out.print("Digite o novo nome:");
					String nome = sc.nextLine();
					cliente.setNome(nome);
					sc.nextLine();
					clienteController.atualizarCliente(cliente);

					System.out.println("\nDados do cliente atualizado com sucesso:\n " + cliente);

				}
				if (cliente instanceof PessoaJuridica) {
					sc.nextLine();
					System.out.print("Digite o novo endereï¿½oo:");
					String endereco = sc.nextLine();
					cliente.setEndereco(endereco);
					System.out.print("Digite a nova razï¿½o social:");
					String nome = sc.nextLine();
					cliente.setNome(nome);

					clienteController.atualizarCliente(cliente);

					System.out.println("\nDados do cliente atualizado com sucesso:\n " + cliente);

				}
			} else {
				System.out.println("\nO cliente nao existe!");
			}

			System.out.println("\nDeseja editar outro cliente? (1)novo cliente (2) para sair:");
			novo = sc.nextInt();

		} while (novo == 1);

	}

	public void editarVeiculo() {
		int novo;

		String tipoV;
		System.out.print("\nDigite (C) - Editar carro: ");
		System.out.print("\nDigite (U) - Editar utilitario: ");
		System.out.print("\nDigite (M) - Editar moto: ");
		tipoV = sc.nextLine();
		tipoV = tipoV.toUpperCase();
		veiculoController.listar(tipoV);

		do {

			System.out.println("\nDigite o id do veiculo:");
			int id = sc.nextInt();

			Veiculo veiculo = veiculoController.consultarVeiculo(id);

			if (veiculo != null) {

				if (veiculo instanceof Carro) {
					sc.nextLine();
					System.out.print("\nDigite o novo modelo:");
					String modelo = sc.next();
					veiculo.setModelo(modelo);
					System.out.print("Digite a nova marca:");
					String marca = sc.next();
					veiculo.setMarca(marca);

					veiculoController.atualizarVeiculo(veiculo);

					System.out.println("\nDados do veiculo atualizado com sucesso:\n " + veiculo);

				} else if (veiculo instanceof Utilitario) {
					sc.nextLine();
					System.out.print("\nDigite o novo modelo:");
					String modelo = sc.next();
					veiculo.setModelo(modelo);
					System.out.print("Digite a nova marca:");
					String marca = sc.next();
					veiculo.setMarca(marca);

					veiculoController.atualizarVeiculo(veiculo);

					System.out.println("\nDados do veiculo atualizado com sucesso:\n " + veiculo);

				} else if (veiculo instanceof Moto) {
					sc.nextLine();
					System.out.print("\nDigite o novo modelo:");
					String modelo = sc.next();
					veiculo.setModelo(modelo);
					System.out.print("Digite a nova marca:");
					String marca = sc.next();
					veiculo.setMarca(marca);

					veiculoController.atualizarVeiculo(veiculo);

					System.out.println("\nDados do veiculo atualizado com sucesso:\n " + veiculo);

				}
			} else {
				System.out.println("\nO veiculo no existe!");
			}

			System.out.print("Deseja editar outro veiculo? (1)novo veiculo (2) para sair:");
			novo = sc.nextInt();

		} while (novo == 1);

	}

	public void editarReserva() {

		int novo;
		reservaController.listar();
		do {

			System.out.println("\nDigite o id da reserva:");
			int id = sc.nextInt();

			Reserva reserva = reservaController.consultarReserva(id);

			if (reserva != null) {
				sc.nextLine();

				System.out.print("\nDigite a nova data check out:");

				Date fim;
				try {
					fim = sdf.parse(sc.nextLine());

					reserva.setFim(fim);

					System.out.print("Digite a nova check in:");
					Date inicio = sdf.parse(sc.nextLine());
					reserva.setInicio(inicio);
					System.out.print("Digite a nova check in:");

					reservaController.atualizarReserva(reserva);
					System.out.println("\nReserva atualizada com sucesso!\n " + reserva);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("A reserva nao existe!");
			}

			System.out.println("\nDeseja editar outra reserva? (1)novo cliente (2) para sair:");
			novo = sc.nextInt();

		} while (novo == 1);

	}

	public void emitirFatura() {
		System.out.print("\nDigite a quantidade de faturas que deseja emitir:");
		int n = sc.nextInt();

		reservaController.listar();
		for (int i = 0; i < n; i++) {

			System.out.print("\nDigite o id da reserva:");
			int id = sc.nextInt();

			Reserva reserva = reservaController.consultarReserva(id);

			System.out.print("\nDigite o valor da diaria:");
			double precoDia = sc.nextDouble();
			System.out.print("Digite o valor por hora:");
			double precoHora = sc.nextDouble();

			ServicoAluguel servicoAluguel = new ServicoAluguel(precoDia, precoHora, new TaxaBrasil());
			servicoAluguel.processoFatura(reserva);

			System.out.println("\n    ****Fatura:****\n");

			System.out
					.println("Pagamento sem taxa:R$" + String.format("%.2f", reserva.getFatura().getPagamentoBasico()));
			System.out.println("Taxa de imposto: R$" + String.format("%.2f", reserva.getFatura().getPagamentoTaxa()));
			System.out.println("Total da fatura:R$" + String.format("%.2f", reserva.getFatura().getpagamentoTotal()));
			System.out.println("\n  **********************");
		}

	}

}
