package vocabulary;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class File {
	public void File(String Filename,String[] array) throws IOException {
		FileReader fr = new FileReader(Filename);
		int i = 0;
		int j;
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			array[i] = br.readLine();
			i++;
		}
		fr.close();
	}
}

