package parser.controller;

import abstract_classes.TrainComponent;
import parser.RichRailCli;
import parser.RichRailParser;
import repository_iterator.Iterator;

public class GetCommand {
	
	private String finalMessage;
	
	RichRailCli cli = new RichRailCli();
	
	public void execute(String id, String type, String option) {	
    	switch(type) {
    	  case "train":
    	        for (Iterator iter = cli.allLocomotives.getIterator(); iter.hasNext();) {
    	        	TrainComponent locomotive = iter.next();
	            	if(locomotive.getId().equals(id)) {
	            		switch(option) {
	            		//check what the user asked for
	            		case "numseats":
        				    System.out.println("Locomotive with id " + id + " has " + locomotive.getNumseats() + " seats.");
        				    finalMessage = "Locomotive with id " + id + " has " + locomotive.getNumseats() + " seats.";
        				    return;
	            		case "maxweight":
        				    System.out.println("Locomotive with id " + id + " is allowed a maximum of " + locomotive.getMaxweight() + " kilograms of cargo.");
        				    finalMessage = "Locomotive with id " + id + " is allowed a maximum of " + locomotive.getMaxweight() + " kilograms of cargo.";
        				    return;
    	            	default:
        				    System.out.println(option + " is not a valid option");
        				    finalMessage = option + " is not a valid option";
        				    return;
	            		}
	            	}
	            }
			    System.out.println("This train does not exist.");
			    finalMessage = "This train does not exist.";
			    return;
    	  case "wagon":
      		for (Iterator iter2 = cli.allWagons.getIterator(); iter2.hasNext();) {
    			TrainComponent wagon = iter2.next();
	            	if(wagon.getId().equals(id)) {
	            		switch(option) {
	            		case "numseats":
        				    System.out.println("Wagon with id " + id + " has " + wagon.getNumseats() + " seats.");
        				    finalMessage = "Wagon with id " + id + " has " + wagon.getNumseats() + " seats.";
        				    return;
	            		case "maxweight":
        				    System.out.println("Wagon with id " + id + " is allowed a maximum of " + wagon.getMaxweight() + " kilograms of cargo.");
        				    finalMessage = "Wagon with id " + id + " is allowed a maximum of " + wagon.getMaxweight() + " kilograms of cargo.";
        				    return;
    	            	default:
        				    System.out.println(option + " is not a valid option.");
        				    finalMessage = option + " is not a valid option.";
        				    return;
	            		}
	            	}
	            }
      			System.out.println("This wagon does not exist.");
      			finalMessage = "This wagon does not exist.";
			    return;
    	  default: 
			System.out.println("This is not a valid train component.");
			finalMessage = "This is not a valid train component.";
		    return;
    	}
    }
	
    public String getFinalMessage() {
		return finalMessage;
    	
    }
}
