package controllers;

import models.Pessoa;

public interface IClienteController {

	 int criarCliente(Pessoa pessoa);

	 Pessoa consultarCliente(Integer id);

	 Pessoa consultarCliente(String cpfCnpj);

	 Pessoa atualizarCliente(Pessoa pessoaParaAtualizar);

	 void deletar(Integer id);

	 void listar(String tipo);

	Pessoa getPessoa(Integer id, String tipo);

}
