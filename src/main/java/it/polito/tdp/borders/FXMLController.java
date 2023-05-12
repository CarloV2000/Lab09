
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
	@FXML
	private ComboBox<Country> cmbCountry;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private TextArea txtResult2;
    
    
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	String input = this.txtAnno.getText();
    	int inputNum;
    	
    	try {
    		inputNum = Integer.parseInt(input);
    	}catch(NumberFormatException e ) {
    		this.txtResult.setText("Inserire un numero!");
    		return;
    	}
    	
    	if(inputNum < 1816 || inputNum > 2016) {
    		this.txtResult.setText("Inserire un anno compreso tra 1816 e 2016!");
    		return;
    	}
    	
    	model.creaGrafo(inputNum);
    	this.txtResult.setText(model.elencoStatiConNumeroStatiConfinanti(inputNum));
    }
    
    @FXML
    void doStatiRaggiungibili(ActionEvent event) {
    	Country c = this.cmbCountry.getValue();
    	String input = this.txtAnno.getText();
    	String s = "";
    	int inputNum;
    	
    	try {
    		inputNum = Integer.parseInt(input);
    	}catch(NumberFormatException e ) {
    		this.txtResult.setText("Inserire un numero!");
    		return;
    	}
    	model.creaGrafo(inputNum);
    	List<Country>confinanti = model.trovaConfinanti(c, inputNum);
    	for(Country x : confinanti) {
    		if(s != "")
    			s += " , ";
    		s += x.getStateName() ;
    	}
    	this.txtResult2.setText("Gli stati confinanti con "+ c.getStateName() + " sono :\n" + s);
    }
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCountry != null : "fx:id=\"cmbCountry\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult2 != null : "fx:id=\"txtResult2\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for(Country c : this.model.getCountries()) {
    		this.cmbCountry.getItems().add(c);
    	}
    }
}
