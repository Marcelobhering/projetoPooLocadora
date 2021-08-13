package models;

import models.enums.TipoCorEnum;
import models.enums.TipoMotoEnum;

import java.math.BigDecimal;

public class Moto extends Veiculo {

	private TipoMotoEnum tipo;

	public Moto() {
	}

	public Moto(Integer id, String placa, String marca, String modelo, TipoCorEnum cor, long kilometragem,
			int quantidadePassageiros, TipoMotoEnum tipo) {
		super(id, placa, marca, modelo, cor, kilometragem, quantidadePassageiros);
		this.tipo = tipo;
	}

	public Moto(Integer id, String placa, boolean alugado, String marca, String modelo, TipoCorEnum cor,
			long kilometragem, int quantidadePassageiros, Double valorDiaria, Double valorHora, TipoMotoEnum tipo,
			Double seguroAdicional) {
		super(id, placa, alugado, marca, modelo, cor, kilometragem, quantidadePassageiros, valorDiaria, valorHora);
		this.tipo = tipo;

	}

	public TipoMotoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoMotoEnum tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "\nTipo = " + tipo + super.toString();
	}
    
    
}
