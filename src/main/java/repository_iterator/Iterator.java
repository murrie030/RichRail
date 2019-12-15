package repository_iterator;

import abstract_classes.TrainComponent;

public interface Iterator {
	   public boolean hasNext();
	   public TrainComponent next();
	}