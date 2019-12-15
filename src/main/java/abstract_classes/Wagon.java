package abstract_classes;

import java.io.Serializable;

public abstract class Wagon implements TrainComponent, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private int numseats;
	private float maxweight;
	
	private boolean isAttached;
	
	public Wagon(String id) {
		this.id = id;
		this.isAttached = false;
	}
	
	public Wagon(String id, int numseats) {
		this.id = id;
		this.numseats = numseats;
		this.isAttached = false;
	}
	
	public Wagon(String id, float maxweight) {
		this.id = id;
		this.maxweight = maxweight;
		this.isAttached = false;
	}
	
	public Wagon(String id, int numseats, float maxweight) {
		this.id = id;
		this.numseats = numseats;
		this.maxweight = maxweight;
		this.isAttached = false;
	}
	
	public boolean isAttached() {
		return isAttached;
	}

	public void setAttached(boolean isAttached) {
		this.isAttached = isAttached;
	}

	public int getNumseats() {
		return numseats;
	}

	public float getMaxweight() {
		return maxweight;
	}

	public void setNumseats(int numseats) {
		this.numseats = numseats;
	}

	public void setMaxweight(float maxweight) {
		this.maxweight = maxweight;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}