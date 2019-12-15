package parser.controller;

import java.util.List;

import abstract_classes.Locomotive;
import abstract_classes.TrainComponent;
import parser.RichRailCli;
import parser.RichRailParser;
import repository_iterator.Iterator;

//This class represents the del command.
//Its responsibility is deleting wagons and locomotives.
//Deleted locomotives will have all their wagons deleted as well.
//Deleted wagons will be deteached from their wagons before being deleted.

public class DelCommand {
	
	private String finalMessage;
	
	RichRailCli cli = new RichRailCli();
	
	public void execute(String id, String type) {
    	//check if a locomotive or wagon should be deleted
    	switch(type) {
    	case "train":
	        for (Iterator iter = cli.allLocomotives.getIterator(); iter.hasNext();) {
	        	TrainComponent locomotive = iter.next();
            	if(locomotive.getId().equals(id)) {
            		//delete all wagons that were attached to the locomotive
	            	List<TrainComponent> components = ((Locomotive) locomotive).getComponentList();
    				for(TrainComponent wagono : components){
    					for (Iterator iter2 = cli.allWagons.getIterator(); iter2.hasNext();) {
    		    			TrainComponent wagon = iter2.next();
    				    	if(wagon.getId().equals(wagono.getId())) {
    				    		cli.allWagons.remove(wagon);
    				    	}
    					}
    				}
    				components.clear();
            		//delete the locomotive
            		cli.allLocomotives.remove(locomotive);
		    		cli.observer.setAll(cli.repo, cli.allLocomotives, cli.allWagons, cli.persister);
				    System.out.println("Locomotive with id " + id + " has been succesfully deleted. All its attached wagons are deleted as well.");
            		finalMessage = "Locomotive with id " + id + " has been succesfully deleted. All its attached wagons are deleted as well.";
				    return;
            	}
            }   
	        
		case "wagon":
      		for (Iterator iter = cli.allWagons.getIterator(); iter.hasNext();) {
    			TrainComponent wagon = iter.next();
		    	if(wagon.getId().equals(id)) {
		    		//remove the wagon from locomotives if they are attached to one
		            for (Iterator iter2 = cli.allLocomotives.getIterator(); iter2.hasNext();) {
		            	TrainComponent locomotive = iter2.next();
        				for(Iterator iter3 = ((Locomotive) locomotive).getIterator(); iter3.hasNext();){
        					TrainComponent wagono = iter3.next();
        					if(wagono.getId().equals(id)) {
                				((Locomotive) locomotive).removeComponent(wagono);
        					}
        				}
		            }
		            //delete the wagon
		    		cli.allWagons.remove(wagon);
		    		cli.observer.setAll(cli.repo, cli.allLocomotives, cli.allWagons, cli.persister);
				    System.out.println("Wagon with id " + id + " has been succesfully deleted. It shall be detached from its train if it was attached to one.");
				    finalMessage = "Wagon with id " + id + " has been succesfully deleted. It shall be detached from its train if it was attached to one.";
				    return;
		    	}
		    }  
		default:
		    System.out.println(type + " is not a valid component.");
		    finalMessage = type + " is not a valid component.";
		    return;
		}
    }
	
    public String getFinalMessage() {
		return finalMessage;
    	
    }
}
