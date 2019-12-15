package adapter_observer;

import java.io.IOException;

import repository_iterator.RepositoryManager;

public interface Persistence {
	public RepositoryManager loadData() throws ClassNotFoundException, IOException;
	
	public void saveData(RepositoryManager repo) throws IOException;
}
