package rev;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


public class Retrieve {

	private LinkedList<String> memory = new LinkedList<>();
	
	private File output = new File("C:\\Users\\Peter Leonard\\OneDrive\\Documents\\java\\RevisionHelper\\src\\rev\\memory.txt");
	
	public LinkedList retrieve(){
		try {
			Scanner sc = new Scanner(output);
			
			while(sc.hasNext()) {
				memory.add(sc.nextLine());
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return memory;
	}
	
	public void write(LinkedList<String> al) {
		 try {
			FileWriter fw = new FileWriter(output);
			
			for(String s: al) {
				fw.write(s + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
