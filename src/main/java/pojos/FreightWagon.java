package pojos;

import abstract_classes.Wagon;

public class FreightWagon extends Wagon{

	private static final long serialVersionUID = 1L;
	public FreightWagon(String id, float maxweight) {
		super(id, maxweight);
	}
}