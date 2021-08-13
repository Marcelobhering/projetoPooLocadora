package models;

import models.enums.TipoCorEnum;
import models.enums.TipoUtilitarioEnum;

public class Utilitario extends Veiculo {

	private TipoUtilitarioEnum tipo;

	public Utilitario() {
	}

	public Utilitario(Integer id, String placa, String marca, String modelo, TipoCorEnum cor, long kilometragem,
			int capacidadeCarga, TipoUtilitarioEnum tipo) {
		super(id, placa, marca, modelo, cor, kilometragem, capacidadeCarga);
		this.tipo = tipo;
	}

	public Utilitario(Integer id, String placa, boolean alugado, String marca, String modelo, TipoCorEnum cor,
			long kilometragem, int capacidadeCarga, Double valorDiaria, Double valorHora, TipoUtilitarioEnum tipo) {
		super(id, placa, alugado, marca, modelo, cor, kilometragem, capacidadeCarga, valorDiaria, valorHora);
		this.tipo = tipo;
	}

	public TipoUtilitarioEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoUtilitarioEnum tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "\n\n" + "Tipo = " + tipo + super.toString();
	}

}
	
	
	
	
	
	
	

    
    

