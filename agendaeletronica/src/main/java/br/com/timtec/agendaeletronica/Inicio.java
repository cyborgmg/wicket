package br.com.timtec.agendaeletronica;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class Inicio extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Inicio() {
		Label mensagemBoasVindas = new Label("mensagemBoasVindas", Model.of("Bem vindo Agenda Eletronica")); 
		this.add(mensagemBoasVindas);
	}

}
