package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	public List<String> getNomeCorsi() {
		CorsoDAO dao = new CorsoDAO();
		List<String> nomi=new LinkedList<String>();
		for(Corso c:dao.getTuttiICorsi()) {
			nomi.add(c.getNome());
		}
		return nomi;
	}
	
	public Studente getStudente(int matr) {
		StudenteDAO dao=new StudenteDAO();
		return dao.getStudentePerMatr(matr);
	}
	
	public Corso getCorso(String nome) {
		CorsoDAO dao = new CorsoDAO();
		Corso corso=null;
		for(Corso c:dao.getTuttiICorsi()) {
			if(c.getNome().equals(nome))
				corso=c;
		}
		return corso;
	}
	
	public String getStudenti(Corso corso){
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(corso);
	}
	
	public String getCorsi(int matr) {
		StudenteDAO dao=new StudenteDAO();
		return dao.getCorsi(matr).toString();
	}
	
	public boolean getEIscritto(int matr, String cod) {
		StudenteDAO dao=new StudenteDAO();
		List<Corso> corsi=dao.getCorsi(matr);
		boolean trovato=false;
		for(Corso c:corsi)
			if(c.getCodins().equals(cod))
				trovato=true;
		return trovato;
	}
	
	public boolean setIscrizione(int matr, String cod) {
		CorsoDAO dao = new CorsoDAO();
		return dao.iscriviStudenteACorso(matr,cod);
	}
	
	

}
