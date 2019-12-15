package pojos;

import abstract_classes.Wagon;

public class PlainWagon extends Wagon{

	private static final long serialVersionUID = 1L;

	public PlainWagon(String id, int numseats, float maxweight) {
		super(id, numseats, maxweight);
	}
}