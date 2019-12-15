package repository_iterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import abstract_classes.TrainComponent;

public class LocomotiveRepository implements Container, Repository<TrainComponent>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<TrainComponent> allLocomotives = new ArrayList<TrainComponent>();
	
	public Iterator getIterator() {
	      return new LocomotiveIterator();
	}
	
	private class LocomotiveIterator implements Iterator{

	    int index;

	    @Override
	    public boolean hasNext() {
	    
	       if(index < allLocomotives.size()){
	          return true;
	       }
	       return false;
	    }

	    @Override
	    public TrainComponent next() {
	    
	       if(this.hasNext()){
	          return allLocomotives.get(index++);
	       }
	       return null;
	    }	

	}

	public void add(TrainComponent locomotive) {
		
		this.allLocomotives.add(locomotive);
	}	
    
	public void remove(TrainComponent locomotive) {
		this.allLocomotives.remove(locomotive);
	}
}