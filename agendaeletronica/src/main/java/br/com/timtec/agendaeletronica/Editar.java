package br.com.timtec.agendaeletronica;

import java.sql.Connection;

import org.apache.wicket.markup.html.basic.Label;

import br.com.timtec.agendaeletronica.contato.Contato;
import br.com.timtec.agendaeletronica.contato.ContatoDAO;

public class Editar extends Criar {

	private static final long serialVersionUID = -7015139871654484352L;

	public Editar(Contato contato) {
		super(contato);
		replace(new Label("titulo","Edição de Contato"));
	}
	
	@Override
	protected void salvar(Contato contatoSubmetido) {
		
		 Connection	conexao = ((WicketApplication) getApplication()).getConexao();
		 ContatoDAO dao = new ContatoDAO(conexao); 
		 dao.atualizar(contatoSubmetido);
	}

}
