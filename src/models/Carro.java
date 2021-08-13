package models;

import models.enums.TipoCarroEnum;
import models.enums.TipoCorEnum;

import java.math.BigDecimal;

public class Carro extends Veiculo {

	private TipoCarroEnum tipo;

	public Carro() {
	}

	public Carro(Integer id, String placa, String marca, String modelo, TipoCorEnum cor, long kilometragem,
			int quantidadePassageiros, TipoCarroEnum tipo) {
		super(id, placa, marca, modelo, cor, kilometragem, quantidadePassageiros);
		this.tipo = tipo;
	}

	public Carro(Integer id, String placa, boolean alugado, String marca, String modelo, TipoCorEnum cor,
			long kilometragem, int quantidadePassageiros, Double valorDiaria, Double valorHora, TipoCarroEnum tipo) {
		super(id, placa, alugado, marca, modelo, cor, kilometragem, quantidadePassageiros, valorDiaria, valorHora);
		this.tipo = tipo;
	}

	public TipoCarroEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarroEnum tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "\n\n" + "Tipo = " + tipo + super.toString();
	}

}
	
	
	
	
	
	
	
	

