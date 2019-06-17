package br.com.timtec.agendaeletronica;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class BasePage extends WebPage {

	private static final long serialVersionUID = -1194325343997063958L;
	
	public BasePage() {
		
		String userName = (String) getSession().getAttribute("nomeUsuario");
		
		if(userName==null) {
			setResponsePage(Login.class);
			return;
		}
		
		this.add(new Link<Void>("Sair") {

			private static final long serialVersionUID = 5546396717733802845L;

			@Override
			public void onClick() {
				getSession().invalidate();
				setResponsePage(Login.class);
			}
		});
	}

}



















