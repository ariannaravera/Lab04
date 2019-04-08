package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnOK;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matr=0;
    	try {
    		matr=Integer.parseInt(txtMatricola.getText());}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
    		return;
    	}
    	txtResult.appendText(model.getCorsi(matr));
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	String nc=comboCorsi.getValue();
    	Corso corso=model.getCorso(nc);
    	if(txtMatricola.getText().equals("")) {
    		if(corso==null) {
        		txtResult.appendText("Errore. Scegliere un corso.");
        	}
        	else{
        		txtResult.appendText(model.getStudenti(corso));
        	}
    	}
    	else {
    		int matr=0;
        	try {
        		matr=Integer.parseInt(txtMatricola.getText());}
        	catch(NumberFormatException e) {
        		txtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
        		return;
        	}
        	if(model.getEIscritto(matr, corso.getCodins())==true)
    			txtResult.appendText("Studente già iscritto a questo corso.\n");
    		else {
    			txtResult.appendText("Studente non iscritto a questo corso.\n");
    		}
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	String nc=comboCorsi.getValue();
    	Corso corso=model.getCorso(nc);
    	
    	if(corso==null) {
    		txtResult.appendText("Errore. Scegliere un corso.\n");
    	}
    	else{
    		int matr=0;
           	try {
          		matr=Integer.parseInt(txtMatricola.getText());}
           	catch(NumberFormatException e) {
           		txtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
           		return;
           	}
           	if(model.getEIscritto(matr, corso.getCodins())==true)
       			txtResult.appendText("Studente già iscritto a questo corso.\n");
           	else {
        		if(model.setIscrizione(matr,corso.getCodins())==true)
           			txtResult.appendText("Studente iscritto al corso!\n");
        		else
           			txtResult.appendText("Problema nell'iscrizione.\n");
        		
       		}
    	}
    }

    @FXML
    void doOK(ActionEvent event) {
    	int matr=0;
    	try {
    		matr=Integer.parseInt(txtMatricola.getText());}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
    	}
    	Studente studente=model.getStudente(matr);
    	if(studente==null) {
    		txtResult.appendText("Errore. Non esistono studenti con la matricola indicata.\n");
    	}
    	else {
    		txtNome.appendText(studente.getNome());
    		txtCognome.appendText(studente.getCognome());
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    }

    @FXML
    void initialize() {
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	List<String> corsi=new LinkedList<String>(model.getNomeCorsi());
    	corsi.add("");
    	comboCorsi.getItems().addAll(corsi);
    }
}

