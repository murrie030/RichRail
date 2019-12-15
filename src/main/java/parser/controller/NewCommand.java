package parser.controller;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.antlr.v4.runtime.tree.TerminalNode;

import abstract_classes.Locomotive;
import abstract_classes.TrainComponent;
import abstract_classes.Wagon;
import factory.LocomotiveBasedTrainFactory;
import factory.TrainFactory;
import factory.WagonBasedTrainFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import parser.RichRailCli;
import parser.RichRailParser;
import repository_iterator.Iterator;

public class NewCommand {
	
	private String finalMessage;
	
	RichRailCli cli = new RichRailCli();

	public void execute(String id, int numseats, float maxweight, String type) {

        if(type.equals("train"))
        {
        	for (Iterator iter = cli.allLocomotives.getIterator(); iter.hasNext();) {
        		TrainComponent loco = iter.next();
            	if(loco.getId().equals(id)) {
				    System.out.println("There already exists a train with this name, please choose another one.");
				    finalMessage = "There already exists a train with this name, please choose another one.";
				    return;
            	}
            }

        	TrainFactory factory = new LocomotiveBasedTrainFactory(id, numseats, maxweight);
     
            Locomotive loco = (Locomotive) factory.createTrainComponent();
            
            cli.allLocomotives.add(loco);
            
            for (Iterator iter = cli.allLocomotives.getIterator(); iter.hasNext();) {
            	String locoid = iter.next().getId();
			    System.out.println(locoid);
            }
            
            cli.observer.setLocomotives(cli.repo, cli.allLocomotives, cli.persister);
		    	System.out.println("Train with id " + loco.getId() + " has been created.");
		    	finalMessage = "Train with id " + loco.getId() + " has been created.";
			    return;
        }
        
        else if(type.equals("wagon")) 
        {
        	for (Iterator iter = cli.allWagons.getIterator(); iter.hasNext();) {
        		String wagonid = iter.next().getId();
        		if(wagonid.equals(id)) {
				    System.out.println("There already exists a wagon with this name, please choose another one.");
				    finalMessage = "There already exists a wagon with this name, please choose another one.";
				    return;
            	}
            }
            
        	TrainFactory factory = new WagonBasedTrainFactory(id, numseats, maxweight);
     
            Wagon wagon = (Wagon) factory.createTrainComponent();
            
            cli.allWagons.add(wagon);
            
            for (Iterator iter = cli.allWagons.getIterator(); iter.hasNext();) {
            	String wagonid = iter.next().getId();
			    System.out.println(wagonid);
            }
            
            cli.observer.setWagons(cli.repo, cli.allWagons, cli.persister);
		    	System.out.println("Wagon with name " + wagon.getId() + " has been created.");
		    	finalMessage = "Wagon with name " + wagon.getId() + " has been created.";
			    return;
        }
	    System.out.println("This is not a valid train component.");
	    finalMessage = "This is not a valid train component.";
	    return;
    }
	
    public String getFinalMessage() {
		return finalMessage;
    	
    }
}
