package parser;

import java.io.IOException;

import abstract_classes.TrainComponent;
import adapter_observer.PersistenceAdapter;
import adapter_observer.Observer;
import parser.RichRailBaseListener;
import parser.RichRailParser;
import repository_iterator.Iterator;
import repository_iterator.LocomotiveRepository;
import repository_iterator.RepositoryManager;
import repository_iterator.WagonRepository;
import parser.controller.*;

public class RichRailCli extends RichRailBaseListener {
	
	public String finalMessage = null;
	
	public PersistenceAdapter persister = new PersistenceAdapter();
	public Observer observer = new Observer();
	public RepositoryManager repo = new RepositoryManager(); {
	
		try{
			 repo = persister.loadData();
		}
		catch(IOException | ClassNotFoundException e)
		{
			System.out.println("No data has been found yet. This is perfectly normal if you're using this software for the first time, a new instance shall now be created.");
		}
	}
	
    public LocomotiveRepository allLocomotives = repo.getLocomotives();
    public WagonRepository allWagons = repo.getWagons();
  
    @Override
    public void enterNewcommand(RichRailParser.NewcommandContext ctx) {
        String id = ctx.ID().getText();
        String type = ctx.type().getText();
        
        //set a default value if the user didn't give a parameter
        int numseats;
        float maxweight;
        
        try {
        	numseats = Integer.parseInt(ctx.NUMSEATS().toString());
        }
        catch(NullPointerException e) {
        	numseats = 0;
        }
        
        try {
        	maxweight = Float.parseFloat(ctx.MAXWEIGHT().toString());
        }
        catch(NullPointerException e) {
        	maxweight = 0;
        }
        
        NewCommand command = new NewCommand();
        command.execute(id, numseats, maxweight, type);
        finalMessage = command.getFinalMessage();
    }
    
    public void enterAddcommand(RichRailParser.AddcommandContext ctx) {
		String wagonid = ctx.ID().get(0).getText();
        String locoid = ctx.ID().get(1).getText();
        AddCommand command = new AddCommand();
        command.execute(wagonid, locoid);
        finalMessage = command.getFinalMessage();
    }
    
    public void enterGetcommand(RichRailParser.GetcommandContext ctx) {
		String id = ctx.ID().toString();
    	String type = ctx.type().getText();
    	String option = ctx.option().getText();
        GetCommand command = new GetCommand();
        command.execute(id, type, option);
        finalMessage = command.getFinalMessage();
    }
    
    public void enterDelcommand(RichRailParser.DelcommandContext ctx) {
		String id = ctx.ID().toString();
    	String type = ctx.type().getText();
        DelCommand command = new DelCommand();
        command.execute(id, type);
        finalMessage = command.getFinalMessage();
    }
    
    public void enterRemcommand(RichRailParser.RemcommandContext ctx) {
		String wagonid = ctx.ID().get(0).getText();
        String locoid = ctx.ID().get(1).getText();
        RemCommand command = new RemCommand();
        command.execute(wagonid, locoid);
        finalMessage = command.getFinalMessage();
    }
    
    public String getFinalMessage() {
    	return finalMessage;
    }
}
