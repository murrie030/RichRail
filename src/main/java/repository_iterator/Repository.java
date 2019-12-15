package repository_iterator;

import abstract_classes.TrainComponent;

public interface Repository<components> {
    void add(TrainComponent component);

    void remove(TrainComponent component);
}
