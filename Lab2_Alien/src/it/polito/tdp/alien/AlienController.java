package it.polito.tdp.alien;

/**
 * Sample Skeleton for 'Alien.fxml' Controller Class
 */



import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlienController {
	
	Dizionario dizionario = new Dizionario(); // creo il dizionario
	
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField txtWord;
    @FXML
    private TextArea txtResult;
    @FXML
    private Button btnTranslate;
    @FXML
    private Button btnReset;
        
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Alien.fxml'.";
    	txtResult.appendText("Welcome to Alien Dictionary v2016.\n");
    	assert btnTranslate != null : "fx:id=\"bntTranslate\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Alien.fxml'.";
    	
    }
  
    
    @FXML
    void doTranslate(ActionEvent event) {
    	String word = txtWord.getText(); // prendo il testo dalla textField
    	word=word.trim();
    	
    	
    	
    	if (containsWhiteSpace(word)) { // caso AGGIUNGI
    		String[] words = word.split(" ");
    		if (isAlpha(words[0]) && isAlpha(words[1])) { // se Lettere
    			try {
    				dizionario.aggiungiVoce(new Voce(words[0], words[1]));
    				txtResult.appendText("Aggiunta al dizionario una traduzione di "+words[0]+"\n");
    			} catch (TraduzioneGiaPresenteException e) {
    				txtResult.appendText("Traduzione già presente nel dizionario\n");
    			}
    		}
    		else 
    			txtResult.appendText("Non inserire carateri speciali\n");
    		
    	 }
    	
    	else { // caso TRADUCI
    		try {
				txtResult.appendText("Traduzione di "+word+": "+dizionario.traduci(word)+"\n");
			} catch (ParolaInesistenteException e) {
				txtResult.appendText("Parola non presente nel dizionario\n");
			}
    	}
    		
    }

    
    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.setText("Welcome to Alien Dictionary v2016.\n");

    }
    
    public boolean isAlpha(String name) {
    	return name.matches("[a-zA-Z]+");
    }
    
    public boolean containsWhiteSpace (String testCode) {
    	if (testCode !=null) {
    		for (int i = 0; i<testCode.length(); i++) {
    			if (Character.isWhitespace(testCode.charAt(i))) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
}
