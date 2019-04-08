package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				
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
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String cod) {
		final String sql = "SELECT * FROM corso WHERE codins = ?";
		Corso c = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, cod);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				c = new Corso(rs.getString("codins"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getInt("pd"));
				
			}
			rs.close();
			conn.close();
			
			return c;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public String getStudentiIscrittiAlCorso(Corso corso) {
		String cod=corso.getCodins();
		final String sql = "SELECT matricola, cognome, nome, CDS FROM studente WHERE matricola IN (SELECT matricola FROM iscrizione WHERE codins=?)";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, cod);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Studente s = new Studente(rs.getInt("matricola"),
						rs.getString("cognome"),
						rs.getString("nome"),
						rs.getString("CDS"));
				studenti.add(s);
			}
			rs.close();
			conn.close();

			return studenti.toString();

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(int matr, String cod) {
		final String sql ="INSERT INTO iscrizione (matricola, codins) VALUES(?,?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matr);
			st.setString(2, cod);
			//RIVEDO PERCHE COSI NON VA E NON HA SENSTO RETURN TRUE
			boolean rs = st.execute();
			
			conn.close();

			return true;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}
