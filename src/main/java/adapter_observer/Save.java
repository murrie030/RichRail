package adapter_observer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import repository_iterator.RepositoryManager;

public class Save {
	
	public void saveData(RepositoryManager repo) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File("src/main/java/adapter_observer/Persistence.obj"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(repo);
		oos.close();
	}
}
