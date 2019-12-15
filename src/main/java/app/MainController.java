package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.ibm.icu.text.SimpleDateFormat;

import abstract_classes.Locomotive;
import abstract_classes.TrainComponent;
import abstract_classes.Wagon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import parser.RichRailCli;
import parser.controller.NewCommand;
import repository_iterator.Iterator; 

public class MainController implements Initializable, Command{
	
	@FXML
	public Button executebtn;
	@FXML
	public Button trainbtn;
	@FXML
	public Button wagonbtn;
	@FXML
	public Button logger;
	@FXML
	public ComboBox<String> locomotiveComboBox;
	@FXML
	public ComboBox<String> wagonComboBox;
	@FXML
	public Label selectedLocomotive;
	@FXML
	public Label selectedWagon;
	@FXML
	public TextField id;
	@FXML
	public TextField numseats;
	@FXML
	public TextField maxweight;
	@FXML
	public TextField idwagon;
	@FXML
	public TextField command;
	@FXML
	public TextField numseatswagon;
	@FXML
	public TextField maxweightwagon;
	@FXML
	public TextArea commandview;
	@FXML
	public TextArea commandview2;
	@FXML
	public TextArea trainview;
	
	
	/*
	 * Define an ObservableList and add the elements. This ObservableList
	 * is used in the initialize() method
	 */
	ObservableList<String> locomotiveList = FXCollections.observableArrayList("TestLocomotive1", "TestLocomotive2");
	ObservableList<String> wagonList = FXCollections.observableArrayList("TestWagon1", "TestWagon2");
	
	
	/*
	 * This method initializes the ComboBox component and calls 
	 * the updateView() method
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		locomotiveComboBox.setItems(locomotiveList);
		wagonComboBox.setItems(wagonList);
		
		updateView(); 		// Call the updateView() method
	}
	
	
	/*
	 * This method sets the text of the label when the locomotiveComboBox is 
	 * selected 
	 */
	public void comboChanged(ActionEvent event) {
		selectedLocomotive.setText("Selected locomotive: \n" + locomotiveComboBox.getValue());
	}
	
	
	/*
	 * This method sets the text of the label when the wagonComboBox is 
	 * selected 
	 */
	public void comboChanged2(ActionEvent event) {
		selectedWagon.setText("Selected wagon: \n" + wagonComboBox.getValue());
	}
	
	
	/*
	 * This method ensures that the value of a train name textfield will be 
	 * set (dynamic) to the ComboBox
	 */
	public void addTrainName(ActionEvent event) {
		String trainName = id.getText();
		
		locomotiveComboBox.getItems().addAll(trainName);
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("The " + id.getText() +  " locomotive is added successfully");
		alert.show();
	}
	
	
	/*
	 * This method ensures that the value of a wagon name textfield will be
	 * set (dynamic) to the ComboBox
	 */
	public void addWagonName(ActionEvent event) {
		String wagonName = idwagon.getText();
		
		wagonComboBox.getItems().addAll(wagonName);
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("The " + idwagon.getText() + " wagon is added successfully");
		alert.show();
	}
	
	
	/*
	 * This method gets the values of the textfield of a train and shows a message 
	 * in the commandview if the train is created or not
	 */
	public void getTrainFields (ActionEvent event) {
		System.out.println(id.getText() + " " + numseats.getText() + " " + maxweight.getText());
		int numseats2 = 0;
		float maxweight2 = 0;
		
		try {
			numseats2 = Integer.parseInt(numseats.getText());
		}
		
		catch(NumberFormatException e) {
			numseats2 = 0;
		}
		
		try {
			maxweight2 = Float.parseFloat(maxweight.getText());
		}
		
		catch(NumberFormatException e) {
			maxweight2 = 0;
		}
		
		NewCommand command = new NewCommand();
		command.execute(id.getText(), numseats2, maxweight2, "train");
		commandview2.appendText(command.getFinalMessage());
		commandview2.appendText("\n");
		
		addTrainName(event);
	}
	
	
	/*
	 * This method gets the values of the textfield of a wagon and shows a message 
	 * in the commandview if the wagon is created or not
	 */
	public void getWagonFields (ActionEvent event) {
		System.out.println(id.getText() + " " + numseats.getText() + " " + maxweight.getText());
		int numseats2 = 0;
		float maxweight2 = 0;
		
		try {
			numseats2 = Integer.parseInt(numseatswagon.getText());
		} catch(NumberFormatException e) {
			numseats2 = 0;
		}
		
		try {
			maxweight2 = Float.parseFloat(maxweightwagon.getText());
		} catch(NumberFormatException e) {
			maxweight2 = 0;
		}
		
		NewCommand command = new NewCommand();
		command.execute(id.getText(), numseats2, maxweight2, "wagon");
		commandview2.appendText(command.getFinalMessage());
		commandview2.appendText("\n");
	}
	
	
	/*
	 * This method ensures that the commands can be executed
	 */
	public void execute(ActionEvent event) {
		CLI cli = new CLI();
		commandview.appendText(command.getText());
		commandview.appendText("\n");
		commandview2.appendText(cli.bootUp(command.getText()));
		commandview2.appendText("\n");
		trainview.clear();
		updateView();
	}
	
	
	/*
	 * This method is to log the output of the executed commands
	 */
	public void logToFile(ActionEvent event) throws IOException {
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.txt'").format(new Date());
		PrintWriter writer = new PrintWriter("log_"+fileName, "UTF-8");
		writer.write(commandview2.getText());
		writer.close();
	}
	
	
	/*
	 * This method shows an overview of the trains and wagons
	 */
	public void updateView() {
	    RichRailCli cli = new RichRailCli();
		String viewText = "";
		// Write the locomotives and their attached wagons to the screen
        for (Iterator iter = cli.allLocomotives.getIterator(); iter.hasNext();) {
        	TrainComponent locomotive = iter.next();
        	viewText = "(<" + locomotive.getClass().getSimpleName() + ">" + locomotive.getId() + ")";
			for(TrainComponent wagon : ((Locomotive) locomotive).getComponentList()){
				viewText = viewText + "-----" + "(<" + wagon.getClass().getSimpleName() + ">" + wagon.getId() + ")";
			}
			trainview.appendText(viewText);
			trainview.appendText("\n");
        }
        // Write the remaining loose wagons to the screen
        trainview.appendText("\n");
  		for (Iterator iter2 = cli.allWagons.getIterator(); iter2.hasNext();) {
			TrainComponent wagon = iter2.next();
			if(((Wagon) wagon).isAttached() == false){
				trainview.appendText("(<" + wagon.getClass().getSimpleName() + ">" + wagon.getId() + "), ");
			}
	    }
    }
}