package br.com.timtec.agendaeletronica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see br.com.timtec.agendaeletronica.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	Connection conexao;
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Inicio.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		conexao = criaConexao();
	}

	private Connection criaConexao() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new UnsupportedOperationException(e.getMessage());
		}
		
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "password");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Connection getConexao() {
		return conexao;
	}
}
