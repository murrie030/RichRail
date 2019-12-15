package factory;

import abstract_classes.TrainComponent;
import pojos.PlainLocomotive;

public class LocomotiveBasedTrainFactory implements TrainFactory {
	
	private String id;
	private int numseats;
	private float maxweight;
	
	public LocomotiveBasedTrainFactory(String id, int numseats, float maxweight) {
		this.id = id;
		this.numseats = numseats;
		this.maxweight = maxweight;
	}
	
	public TrainComponent createTrainComponent() {

		PlainLocomotive loco = new PlainLocomotive(id, numseats, maxweight);
		return loco;
		
	}
}
