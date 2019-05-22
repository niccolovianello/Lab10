package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

	Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private Button btnTrova;

    @FXML
    private Button btnSequenza;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
       	try {
       		
       		boxSecondo.setDisable(false);
        	btnSequenza.setDisable(false);
        	
       		txtResult.clear();
       		model.creaGrafo();
       		for(Author a : model.getNeighbors(boxPrimo.getValue()))
       			txtResult.appendText(a.getFirstname()+" "+a.getLastname()+" "+a.getId()+"\n");
       		
       		List<Author> rimanenti = this.model.getAllAuthors();
       		rimanenti.removeAll(model.getNeighbors(boxPrimo.getValue()));
       		
       		boxSecondo.getItems().addAll(rimanenti);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}

    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnSequenza != null : "fx:id=\"btnSequenza\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	boxPrimo.getItems().addAll(this.model.getAllAuthors());
    	boxSecondo.setDisable(true);
    	btnSequenza.setDisable(true);
    }
}
