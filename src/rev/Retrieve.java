package rev;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Retrieve {

	private ArrayList<String> memory = new ArrayList<>();
	
	private File output = new File("C:\\Users\\Peter Leonard\\Documents\\Java\\RevisionHelper\\src\\rev\\memory.txt");
	
	public ArrayList retrieve(){
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
	
	public void write(ArrayList<String> al) {
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
