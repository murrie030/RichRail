package repository_iterator;

import java.io.Serializable;

public class RepositoryManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public LocomotiveRepository locomotives = new LocomotiveRepository();
	public WagonRepository wagons = new WagonRepository();
	
	public LocomotiveRepository getLocomotives() {
		return locomotives;
	}
	public WagonRepository getWagons() {
		return wagons;
	}
	public void setLocomotives(LocomotiveRepository locomotives) {
		this.locomotives = locomotives;
	}
	public void setWagons(WagonRepository wagons) {
		this.wagons = wagons;
	}
	
	
}
