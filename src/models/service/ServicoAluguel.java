package models.service;

import models.Fatura;
import models.Reserva;
import models.service.ITaxaServico;

public class ServicoAluguel {

	private Double precoDia;
	private Double precoHora;
	
	// associaçao com a interface
	private ITaxaServico taxaServico;   


	public ServicoAluguel(Double precoDia, Double precoHora, ITaxaServico taxaServico) {
		super();
		this.precoDia = precoDia;
		this.precoHora = precoHora;
		this.taxaServico = taxaServico;
	}
	// gera a fatura
	public void processoFatura(Reserva reserva) {                   
		
		long tempo1= reserva.getInicio().getTime();                
		long tempo2=reserva.getFim().getTime();
		double horas = (double)(tempo2-tempo1)/1000 /60/60;              
		// converter miliseg para segundos(/1000) de seg. minutos(/60)e seg. para horas  (/60)      
		double pagamentoBasico;	
		
		
		if(horas <=12.0) {
			pagamentoBasico=Math.ceil(horas)*precoHora;
		
		}else {
			pagamentoBasico=Math.ceil(horas/24)*precoDia;
			
		}
		// cacular o valor do imposto a partir da quantidade passada como parametro
		double taxa = taxaServico.taxa(pagamentoBasico);                      
			
			reserva.setFatura(new Fatura(pagamentoBasico,taxa));
		}
	
	

}

