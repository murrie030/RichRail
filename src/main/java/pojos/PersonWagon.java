package pojos;

import abstract_classes.Wagon;

public class PersonWagon extends Wagon{
	
	private static final long serialVersionUID = 1L;
	public PersonWagon(String id, int numseats) {
		super(id, numseats);
	}
}