package controllers;

import models.Model;
import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;
import repositorys.DAO;
import repositorys.PessoaDAO;

public class ClienteController implements IClienteController{

	//
    private PessoaDAO pessoaDAO;

    // CONSTRUTOR
    public ClienteController(){
        this.pessoaDAO = new PessoaDAO().getInstancia();

    }

    public int criarCliente(Pessoa pessoa){ 
    	
    	 pessoa.setId(null); 
    	  int id = this.pessoaDAO.inserir(pessoa);
          return id;
    	 	
    }

    public Pessoa consultarCliente(Integer id){
        Pessoa pessoa = this.pessoaDAO.consultar(id);  
        return pessoa;
    }

  
    public Pessoa consultarCliente(String cpfCnpj){
  
		
		 Pessoa pessoa = this.pessoaDAO.consultar(cpfCnpj);
	        return pessoa;
    }
    
    public void listar(String tipo){

    	pessoaDAO.listar(tipo);

    }
    
    
    public Pessoa getPessoa(Integer id ,String tipo) {
    	Pessoa ps = pessoaDAO.getPessoa(id,tipo);  	
    	return ps;
    		
    }
    
    public Pessoa atualizarCliente(Pessoa pessoaParaAtualizar){
        Pessoa pessoaSalva = consultarCliente(pessoaParaAtualizar.getId());

        pessoaSalva.setEndereco(pessoaParaAtualizar.getEndereco());
        pessoaSalva.setNome(pessoaParaAtualizar.getNome());
        pessoaSalva.setAtivo(pessoaParaAtualizar.isAtivo());
        
        if(pessoaSalva instanceof PessoaFisica){
            PessoaFisica pf = (PessoaFisica) pessoaSalva;
            PessoaFisica pfPraAtualizar = (PessoaFisica) pessoaParaAtualizar; 
            
            pf.setCpf(pfPraAtualizar.getCpf());
            pf.setCnh(pfPraAtualizar.getCnh());
            pf.setDataNascimento(pfPraAtualizar.getDataNascimento());
            
        } else if(pessoaSalva instanceof PessoaJuridica){
            PessoaJuridica pj = (PessoaJuridica) pessoaSalva;
            PessoaJuridica pjPraAtualizar = (PessoaJuridica) pessoaParaAtualizar;
            
            pj.setCnpj(pjPraAtualizar.getCnpj());
        }
        
        Model model = this.pessoaDAO.atualizar(pessoaSalva);
        return (Pessoa)model;
    }
    
    public void deletar(Integer id){
        this.pessoaDAO.deletar(id);
    }
}
