package models.enums;

public enum TipoUtilitarioEnum {

	FURGAO('F', "Furgao"), SUV('S', "Suv"), CAMIONETE('C', "Camionete"), PICAPE('P', "Picape");

	private char codigo;
	private String descricao;

	TipoUtilitarioEnum(char codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static TipoUtilitarioEnum fromCodigo(char codigo) {
		TipoUtilitarioEnum retorno = null;

		for (TipoUtilitarioEnum tipo : values()) {

			if (tipo.getCodigo() == codigo) {
				retorno = tipo;
				break;
			}

		}
		return retorno;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
