package br.com.timtec.agendaeletronica.contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

	private static final String INSERT_SQL = "INSERT INTO CONTATO(NOME,EMAIL,TELEFONE,ESTADOCIVIL) VALUES(?,?,?,?)";
	private static final String LIST_SQL = "SELECT ID_CONTATO, NOME, EMAIL, TELEFONE, ESTADOCIVIL FROM CONTATO WHERE NOME LIKE ?";
	private static final String UPDATE_SQL = "UPDATE CONTATO SET NOME=?, EMAIL=?, TELEFONE=?, ESTADOCIVIL=? WHERE ID_CONTATO=?";
	
	private Connection conexao;

	public ContatoDAO(Connection conexao) {
		this.conexao = conexao; 
	}

	public void inserir(Contato contato) {
		
		PreparedStatement ps=null;
		
		try {
			ps = conexao.prepareStatement(INSERT_SQL);
			
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getTelefone());
			ps.setString(4, contato.getEstadoCivil().toString());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void atualizar(Contato contato) {
		
		PreparedStatement ps=null;
		try {
			ps = conexao.prepareStatement(UPDATE_SQL);
			
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getTelefone());
			ps.setString(4, contato.getEstadoCivil().name());
			ps.setLong(5, contato.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public List<Contato> listaPorNome(String nome){
		
		List<Contato> contatos = new ArrayList<Contato>(); 
		
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try {
			ps = conexao.prepareStatement(LIST_SQL);
			ps.setString(1, nome+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Contato contato = new Contato(); 
				contato.setId(rs.getLong(1));
				contato.setNome(rs.getString(2));
				contato.setEmail(rs.getString(3));
				contato.setTelefone(rs.getString(4));
				contato.setEstadoCivil( EstadoCivil.valueOf(rs.getString(5)) );
				
				contatos.add(contato);
				
			}
			
			return contatos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {}
			}
		}
		
	}

}
