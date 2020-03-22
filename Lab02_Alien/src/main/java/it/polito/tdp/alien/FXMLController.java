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
	
	private Dizionario dizionario = new Dizionario();

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
    	int countPtoInterrogativo = 0;
    	for (Character c: text.toCharArray()) {
    		if (!Character.isLetter(c) && c != ' ' && c != '?') {
    			// INPUT NON VALIDO
    			len = 1000;
    			break;
    		} else if (c == '?') {
    			if (countPtoInterrogativo > 0) {
    				len = 1000;
    				break;
    			}
    			countPtoInterrogativo++;
    		}
    	}
    	
    	
    	switch (len) {
    	case 1:
    		
    		ArrayList<String> words = new ArrayList<String>();
    		words.add(text);
    		if (text.contains("?")) {
    			words = dizionario.searchWildcard(text);
    		}
    		String msg = "";
    		
    		// RICERCA NEL DIZIONARIO
    		if (words.size() != 0) {
    			for (String word: words) {
	    			List<String> trad = this.dizionario.searchWord(word);
	        		
	        		if (trad == null) {
	        			msg = "Nel dizionario non abbiamo la traduzione di \"" + this.insertTxt.getText() + "\".";
	        		} else {
	        			msg += "La traduzione di \"" + word + "\" è:";
	        			for (String elem: trad) {
	        				msg += "\n - \"" + elem + "\"";
	        			}
	        		}
	        		msg += "\n";
    			}
    		}
    		
    		midTextArea.setText(msg);
    		return;
    	case 2:
    		if (text.contains("?")) {
        		midTextArea.setText("\"" + this.insertTxt.getText() + "\" non è un input valido!\n - Inserire una nuova parola e la relativa traduzione secondo il seguente pattern:\n<parola aliena> <traduzione> (separate da uno spazio).\n - Cercare la traduzione di una parola esistente inserendo <parola aliena>.\n - Gli unici caratteri ammessi sono [a-z A-Z]\\n - Consentito un solo punto interrogativo");
    			return;
    		}
    		// INSERIMENTO NEL DIZIONARIO
    		String [] splitted = text.split(" ");
    		this.dizionario.addWord(splitted[0], splitted[1]);
    		midTextArea.setText("Registrata la traduzione di \"" + splitted[0] + "\".");
    		return;
    	default:
    		// MESSAGGIO DI ERRORE
    		midTextArea.setText("\"" + this.insertTxt.getText() + "\" non è un input valido!\n - Inserire una nuova parola e la relativa traduzione secondo il seguente pattern:\n<parola aliena> <traduzione> (separate da uno spazio).\n - Cercare la traduzione di una parola esistente inserendo <parola aliena>.\n - Gli unici caratteri ammessi sono [a-z A-Z]\n - Consentito un solo punto interrogativo");
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
