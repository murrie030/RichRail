package adapter_observer;

import java.io.IOException;

import repository_iterator.LocomotiveRepository;
import repository_iterator.RepositoryManager;
import repository_iterator.WagonRepository;

public class Observer{

    public void setLocomotives(RepositoryManager repo, LocomotiveRepository allLocomotives, PersistenceAdapter persister) {
        try {
        	repo.setLocomotives(allLocomotives);
			persister.saveData(repo);
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

    }
 
    public void setWagons(RepositoryManager repo, WagonRepository allWagons, PersistenceAdapter persister) {
        try {
        	repo.setWagons(allWagons);
			persister.saveData(repo);
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

    }
    
    public void setAll(RepositoryManager repo, LocomotiveRepository allLocomotives, WagonRepository allWagons, PersistenceAdapter persister) {
        try {
        	repo.setWagons(allWagons);
        	repo.setLocomotives(allLocomotives);
			persister.saveData(repo);
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

    }
}