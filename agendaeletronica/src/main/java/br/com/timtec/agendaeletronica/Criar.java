package br.com.timtec.agendaeletronica;

import java.sql.Connection;
import java.util.Arrays;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import br.com.timtec.agendaeletronica.contato.Contato;
import br.com.timtec.agendaeletronica.contato.ContatoDAO;
import br.com.timtec.agendaeletronica.contato.EstadoCivil;

public class Criar extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6619311231415722854L;
	
	public Criar() {
		add( new Label("titulo","Criação de Contatos") );
		
		Contato contato = new Contato();
		CompoundPropertyModel<Contato> compoundPropertyModelContato = new CompoundPropertyModel<Contato>(contato);
		Form<Contato> form = new Form<Contato>("formularioContato",compoundPropertyModelContato) {

			private static final long serialVersionUID = -1620862938532623698L;
			
			@Override
			public void onSubmit() {
				Contato contatoSubmetido = getModelObject();
				
				salvar(contatoSubmetido);
				
				setResponsePage(Inicio.class);
			}

			
			
		};
		this.add(form);
		
		TextField<String> inputNome = new TextField<String>("nome"); 
		TextField<String> inputEmail = new TextField<String>("email");
		TextField<String> inputTelefone = new TextField<String>("telefone");
		
		DropDownChoice<EstadoCivil> comboEstadoCivil = new DropDownChoice<EstadoCivil>("estadoCivil",
				Arrays.asList(EstadoCivil.values()), new IChoiceRenderer<EstadoCivil>() {

					private static final long serialVersionUID = 8547665914013552365L;

					@Override
					public Object getDisplayValue(EstadoCivil object) {
						// TODO Auto-generated method stub
						return object.getLabel();
					}

					@Override
					public String getIdValue(EstadoCivil object, int index) {
						// TODO Auto-generated method stub
						return object.name();
					}
				}); 
		
		form.add(inputNome, inputEmail, inputTelefone, comboEstadoCivil);
		
		inputNome.setLabel(Model.of("Nome do Contato")).setRequired(true).add(StringValidator.maximumLength(10));
		inputEmail.setLabel(Model.of("E-mail do contato")).add(EmailAddressValidator.getInstance());
		
		add(new FeedbackPanel("feedBackMessage",new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
	}
	
	private void salvar(Contato contatoSubmetido) {
		
	 Connection	conexao = ((WicketApplication) getApplication()).getConexao();
	 ContatoDAO dao = new ContatoDAO(conexao); 
	 dao.inserir(contatoSubmetido);
	}

}
