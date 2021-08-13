package models;



public class Fatura {

	private Double pagamentoBasico;

	private Double pagamentoTaxa;

	

	public Fatura() {
	}

	public Fatura(Double pagamentoBasico, Double pagamentoTaxa) {
		this.pagamentoBasico = pagamentoBasico;
		this.pagamentoTaxa = pagamentoTaxa;

	}
	

	public Double getPagamentoBasico() {
		return pagamentoBasico;
	}

	public void setPagamentoBasico(Double pagamentoBasico) {
		this.pagamentoBasico = pagamentoBasico;
	}

	public Double getPagamentoTaxa() {
		return pagamentoTaxa;
	}

	public void setPagamentoTaxa(Double pagamentoTaxa) {
		this.pagamentoTaxa = pagamentoTaxa;
	}

	public Double getpagamentoTotal() {

		return getPagamentoBasico() + getPagamentoTaxa();
	}
}
