package br.com.timtec.agendaeletronica;

import java.sql.Connection;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import br.com.timtec.agendaeletronica.contato.Contato;
import br.com.timtec.agendaeletronica.contato.ContatoDAO;

public class Pesquisa extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6477084019452203275L;
	
	public Pesquisa() {
		Form<String> formularioPesquisa = new Form<String>("formularioPesquisa");
		this.add(formularioPesquisa);
		
		TextField<String> pesquisaNome = new TextField<String>("pesquisaNome", new Model<String>()); 
		formularioPesquisa.add(pesquisaNome);
		
		final WebMarkupContainer divResultados = new WebMarkupContainer("divResultados"); 
		divResultados.setVisible(false);
		divResultados.setOutputMarkupPlaceholderTag(true);
		this.add(divResultados);
		
		PropertyListView<Contato> contatos = new PropertyListView<Contato>("contatos", getContatos(pesquisaNome.getValue())) {

			private static final long serialVersionUID = -5524050561791641071L;

			@Override
			protected void populateItem(final ListItem<Contato> listItem) {
				
				listItem.add(new Label("nome"));
				listItem.add(new Label("email"));
				listItem.add(new Label("telefone"));
				listItem.add(new Label("estadoCivil"));
				listItem.add(new Link<Void>("linkEditar") {

					@Override
					public void onClick() {
						
						setResponsePage(new Editar(listItem.getModelObject()));
						
					}
				});
			}
			
		};
		
		divResultados.add(contatos);
		
		AjaxButton butaoPesquisar = new AjaxButton("butaoPesquisar", formularioPesquisa) {

			private static final long serialVersionUID = -190527203510669081L;
			
			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				divResultados.setVisible(true);
				target.add(divResultados);
			}
			
		}; 
		
		formularioPesquisa.add(butaoPesquisar);
		
	}

	private List<Contato> getContatos(String value) {
		
		Connection	conexao = ((WicketApplication) getApplication()).getConexao();
		ContatoDAO dao = new ContatoDAO(conexao); 
		
	return dao.listaPorNome(value);
	}

}
