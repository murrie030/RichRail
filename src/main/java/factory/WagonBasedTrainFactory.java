package factory;

import abstract_classes.TrainComponent;
import pojos.FreightWagon;
import pojos.PersonWagon;
import pojos.PlainWagon;

public class WagonBasedTrainFactory implements TrainFactory{

	private String id;
	private int numseats;
	private float maxweight;

	public WagonBasedTrainFactory(String id, int numseats, float maxweight) {
		this.id = id;
		this.numseats = numseats;
		this.maxweight = maxweight;
	}
	
	public TrainComponent createTrainComponent() {
		if(numseats > 0 && maxweight == 0) {
			PersonWagon wagon = new PersonWagon(id, numseats);
			return wagon;
		}
		
		if(numseats == 0 && maxweight > 0) {
			FreightWagon wagon = new FreightWagon(id, maxweight);
			return wagon;
		}
		
		else {
			PlainWagon wagon = new PlainWagon(id, numseats, maxweight);
			return wagon;
		}
	}
}
