package repository_iterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import abstract_classes.TrainComponent;

public class WagonRepository implements Container, Repository<TrainComponent>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<TrainComponent> allWagons = new ArrayList<TrainComponent>();
	
	public Iterator getIterator() {
	      return new WagonIterator();
	}
	
	private class WagonIterator implements Iterator{

	    int index;

	    @Override
	    public boolean hasNext() {
	    
	       if(index < allWagons.size()){
	          return true;
	       }
	       return false;
	    }

	    @Override
	    public TrainComponent next() {
	    
	       if(this.hasNext()){
	          return allWagons.get(index++);
	       }
	       return null;
	    }	

	}

	public void add(TrainComponent wagon) {
		
		this.allWagons.add(wagon);
	}
	
	public void remove(TrainComponent wagon) {
		this.allWagons.remove(wagon);
	}
}