package pojos;

import java.util.ArrayList;
import java.util.List;

import abstract_classes.Locomotive;
import abstract_classes.TrainComponent;

public class PlainLocomotive extends Locomotive{

	private static final long serialVersionUID = 1L;
	public List<TrainComponent> wagonList = new ArrayList<TrainComponent>();
	public PlainLocomotive(String id, int numseats, float maxweight) {
		super(id, numseats, maxweight);
		this.wagonList = new ArrayList<TrainComponent>();
	}
    int index;

    @Override
    public boolean hasNext() {
    
       if(index < wagonList.size()){
          return true;
       }
       return false;
    }

    @Override
    public TrainComponent next() {
    
       if(this.hasNext()){
          return wagonList.get(index++);
       }
       return null;
    }	
}
