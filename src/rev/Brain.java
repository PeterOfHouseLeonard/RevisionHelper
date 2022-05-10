package rev;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.time.LocalDate;

public class Brain {
	public static void main(String[] args) {
		Retrieve ret = new Retrieve();
		ArrayDeque<String> mem = new ArrayDeque<>(ret.retrieve());
		Scanner sc = new Scanner(System.in);
		boolean working = true;
		boolean catchException = true;
		int choice = 0;
		LocalDate today = LocalDate.now();
		
		System.out.println("Today is "+today);
		
		
		while(working) {
			System.out.println("Choose:");
			System.out.println("1. Display Memory");
			System.out.println("2. Quit");
			System.out.println("3. Submit");
			
			choice = sc.nextInt();
			System.out.println();
		
			switch(choice) {
			case 1: //print memory
				for(String s : mem) {
					System.out.println(s);
				}
				break;
			case 2: //quit
				working = false;
				break;
			case 3: //submit
				System.out.println("Submit:");
				String out = today + " " + sc.next();
				sc.next();
				sc.next();
				mem.add(out);
				break;
			default:
				System.out.println("Not an option.");
			}
			System.out.println("\n----------------\n");
		}
		ret.write(new ArrayList<String>(mem));
	}
}
