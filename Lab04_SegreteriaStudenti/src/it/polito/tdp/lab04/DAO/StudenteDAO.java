package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudentePerMatr(int matr) {
		
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		Studente s = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matr);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				s = new Studente(rs.getInt("matricola"),
						rs.getString("cognome"),
						rs.getString("nome"),
						rs.getString("CDS"));
				
			}
			rs.close();
			conn.close();
			
			return s;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	public List<Corso> getCorsi(int matr) {
		
		final String sql = "SELECT codins, crediti, nome, pd FROM corso WHERE codins IN (SELECT codins FROM iscrizione WHERE matricola=?)";
		List<Corso> corsi=new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matr);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getInt("pd"));
				
				corsi.add(c);
			}
			rs.close();
			conn.close();
			
			return corsi;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
}
