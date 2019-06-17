package br.com.timtec.agendaeletronica;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class Login extends WebPage {

	private static final long serialVersionUID = 2000737244342477774L;
	
	public Login() {
		
		final TextField<String> campoNomeUsuario = new TextField<String>("campoNomeUsuario", new Model<String>()); 
		final PasswordTextField campoPassword = new PasswordTextField("campoPassword", new Model<String>());
		
		final Label mensagemErroLogin = new Label("mensagemErroLogin", Model.of("Erro ao Realizar Login"));
		mensagemErroLogin.setOutputMarkupPlaceholderTag(true).setVisible(false);
		
		Form<String> formularioLogin = new Form<String>("formularioLogin") {

			private static final long serialVersionUID = 1L;
			
			@Override
			public final void onSubmit() {
				String nomeUsuario = campoNomeUsuario.getModelObject();
				String senha = campoPassword.getModelObject();
				
				if(nomeUsuario.equals("root")&&senha.equals("123")) {
					getSession().setAttribute("nomeUsuario", nomeUsuario);
					setResponsePage(Inicio.class);
				}else {
					mensagemErroLogin.setVisible(true);
				}
				
			}
			
		};
		this.add(mensagemErroLogin, formularioLogin);
		formularioLogin.add(campoNomeUsuario, campoPassword);
	}

}




















