package it.polito.tdp.alien;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Map<String, String> dizionario = new TreeMap<String, String>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField insertTxt;

    @FXML
    private Button translateBtn;

    @FXML
    private TextArea midTextArea;

    @FXML
    private Button clearBtn;

    @FXML
    void doReset(ActionEvent event) {
    	midTextArea.clear();
    	insertTxt.clear();
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	int len = this.insertTxt.getText().split(" ").length;
    	String text = this.insertTxt.getText().toLowerCase();
     	
    	// CONTROLLI SULL'INPUT
    	for (Character c: text.toCharArray()) {
    		if (!Character.isLetter(c) && c != ' ') {
    			// INPUT NON VALIDO
    			len = 1000;
    			break;
    		}
    	}
    	
    	
    	switch (len) {
    	case 1:
    		// RICERCA NEL DIZIONARIO
    		String trad = this.dizionario.get(text);
    		if (trad == null) {
    			midTextArea.setText("Nel dizionario non abbiamo la traduzione di \"" + this.insertTxt.getText() + "\".");
    		} else {
    			midTextArea.setText("La traduzione di \"" + this.insertTxt.getText() + "\" è \"" + trad + "\".");
    		}
    		return;
    	case 2:
    		// INSERIMENTO NEL DIZIONARIO
    		String [] splitted = text.split(" ");
    		this.dizionario.put(splitted[0], splitted[1]);
    		midTextArea.setText("Registrata la traduzione di \"" + splitted[0] + "\".");
    		return;
    	default:
    		// MESSAGGIO DI ERRORE
    		midTextArea.setText("\"" + this.insertTxt.getText() + "\" non è un input valido!\n - Inserire una nuova parola e la relativa traduzione secondo il seguente pattern:\n<parola aliena> <traduzione> (separate da uno spazio).\n - Cercare la traduzione di una parola esistente inserendo <parola aliena>.\n - Gli unici caratteri ammessi sono [a-zA-Z]");
    		return;		
    	}

    }

    @FXML
    void initialize() {
        assert insertTxt != null : "fx:id=\"insertTxt\" was not injected: check your FXML file 'Scene.fxml'.";
        assert translateBtn != null : "fx:id=\"translateBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert midTextArea != null : "fx:id=\"midTextArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
