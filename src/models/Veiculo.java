package models;

import models.enums.TipoCorEnum;

import java.math.BigDecimal;

public abstract class Veiculo extends Model {

	private String placa;

	private boolean alugado = false;

	private String marca;

	private String modelo;

	private TipoCorEnum cor;

	private long kilometragem = 0;

	private int quantidadePassageiros = 0;

	private Double valorDiaria;

	private Double valorHora;

	public Veiculo() {
	}

	public Veiculo(Integer id, String placa, boolean alugado, String marca, String modelo, TipoCorEnum cor,
			long kilometragem, int quantidadePassageiros, Double valorDiaria, Double valorHora) {
		super(id);
		this.placa = placa;
		this.alugado = alugado;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.kilometragem = kilometragem;
		this.quantidadePassageiros = quantidadePassageiros;
		this.valorDiaria = valorDiaria;
		this.valorHora = valorHora;
	}

	public Veiculo(Integer id, String placa, String marca, String modelo, TipoCorEnum cor, long kilometragem,
			int quantidadePassageiros) {
		super(id);
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.kilometragem = kilometragem;
		this.quantidadePassageiros = quantidadePassageiros;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isAlugado() {
		return alugado;
	}

	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public TipoCorEnum getCor() {
		return cor;
	}

	public void setCor(TipoCorEnum cor) {
		this.cor = cor;
	}

	public long getKilometragem() {
		return kilometragem;
	}

	public void setKilometragem(long kilometragem) {
		this.kilometragem = kilometragem;
	}

	public int getQuantidadePassageiros() {
		return quantidadePassageiros;
	}

	public void setQuantidadePassageiros(int quantidadePassageiros) {
		this.quantidadePassageiros = quantidadePassageiros;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	@Override
	public String toString() {
		return "\nPlaca = " + placa + "\nAlugado = " + alugado + "\nMarca = " + marca + "\nModelo = " + modelo
				+ "\nCor = " + cor + "\nKilometragem = " + kilometragem + "\nQuantidadePassageiros = "
				+ quantidadePassageiros + "\nvalorDiaria = " + valorDiaria + "\nvalorHora = " + valorHora
				+ "\nId do Veiculo :" + getId();
	}

}
