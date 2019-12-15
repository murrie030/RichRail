package abstract_classes;

public interface TrainComponent {

	public void setId(String id);
	String getId();
	
	public void setNumseats(int numseats);
	int getNumseats();
	
	public void setMaxweight(float maxweight);
	float getMaxweight();
}