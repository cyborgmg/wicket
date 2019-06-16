package br.com.timtec.agendaeletronica.contato;

public enum EstadoCivil {
	
	
	SOLTEIRO("Solteiro"),
	CASADO("Casado"),
	DIVORCIADO("Divorciado"),
	VIUVO("Viúvo");
	
	private final String label;

	private EstadoCivil(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
