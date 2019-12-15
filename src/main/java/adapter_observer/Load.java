package adapter_observer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import repository_iterator.LocomotiveRepository;
import repository_iterator.RepositoryManager;
import repository_iterator.WagonRepository;

public class Load {
	
	public RepositoryManager repo = new RepositoryManager();
	
	public void loadLocomotives() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("src/main/java/adapter_observer/Persistence.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		RepositoryManager components = (RepositoryManager) ois.readObject();
		
		LocomotiveRepository locomotives = components.getLocomotives();
		
		ois.close();
		
		repo.setLocomotives(locomotives);
	}
	
	public void loadWagons() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("src/main/java/adapter_observer/Persistence.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		RepositoryManager components = (RepositoryManager) ois.readObject();
		
		WagonRepository wagons = components.getWagons();
		
		ois.close();
		
		repo.setWagons(wagons);
	}
	
	public RepositoryManager getRepository() throws ClassNotFoundException, IOException
	{
		loadLocomotives();
		loadWagons();
		return repo;
	}
}