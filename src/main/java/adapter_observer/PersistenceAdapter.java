package adapter_observer;

import java.io.IOException;

import repository_iterator.RepositoryManager;

public class PersistenceAdapter implements Persistence{
	public Load loader = new Load();
	public Save saver = new Save();
	
	public RepositoryManager loadData() throws ClassNotFoundException, IOException{
		
		return(loader.getRepository());
	}
	
	public void saveData(RepositoryManager repo) throws IOException {
		saver.saveData(repo);
	}
}
