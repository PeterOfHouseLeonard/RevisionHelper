package rev;

import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;

public class Brain {
	
	private static Retrieve ret = new Retrieve();
	private static LinkedList<String> memory = ret.retrieve();
	private static Scanner sc = new Scanner(System.in);
	private static boolean working = true;
	private static boolean submitting = true;
	private static boolean enterDeep = false;
	private static int indexOfDeep = memory.indexOf("deep");
	private static int input;
	private static String next;
	private static LocalDate today = LocalDate.now();
	
	static LinkedList<String> mem = new LinkedList<>();
	static LinkedList<String> deep = new LinkedList<>();
	
	public static void main(String[] args) {
		
		char choice;
		
		for(String s:memory) {
			if(s.equals("deep"))
				enterDeep = true;
			else if(enterDeep)
				deep.add(s);
			else
				mem.add(s);
		}
		
		System.out.println("Today is "+today);
		
		
		while(working) {
			System.out.println("Choose:");
			System.out.println("1. Display Memory");
			System.out.println("2. Quit");
			System.out.println("3. Submit");
			System.out.println("4. Remove");
			System.out.println("5. Edit");
			System.out.println("6. Revised");
			System.out.println("7. Choose at random");
			System.out.println("8. Iterate Loop");
			System.out.println("10. View Deep Storage");
			System.out.println("11. Move to Deep Storage");
			System.out.println("12. Move to Main Storage");
			
			choice = sc.nextInt();
			System.out.println();
		
			select(choice);
			System.out.println("\n----------------\n");
		}
		
		//put back into storage
		LinkedList<String> out = new LinkedList<>(mem);
		out.add("deep");
		out.addAll(deep);
		ret.write(out);
	}

	private static void select(int choice) {
		switch(choice) {
		case 1: //print memory
			printMem(mem);
			break;
		case 2: //quit
			working = false;
			break;
		case 3: //submit
			System.out.print("Submit ");
			while(submitting) {
				System.out.println("Next: (type #done to quit)");
				next = sc.next();
				if(next.equals("#done")) submitting = false;
				else {
					String out = today + " " + next + sc.nextLine();
					System.out.println(out);
					mem.add(out);
				}
			}
			break;
		case 4: //remove
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			mem.remove(input-1);
			break;
		case 5: //edit
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			input--;
			System.out.println("New Input:");
			String edit = mem.remove(input);
			edit = edit.substring(0,10);
			next = sc.next();
			mem.add(input,edit +" "+ next + sc.nextLine());
			break;
		case 6: //revised
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			input--;
			next = mem.remove(input);
			next = today +" "+next.substring(10);
			mem.addLast(next);
			break;
		case 7: //random
			int rand = (int) Math.ceil(Math.random()*mem.size());
			next = mem.get(rand);
			System.out.println(next);
			System.out.println("What would you like to do?");
			System.out.println("1. Ignore");
			System.out.println("2. Roll Again");
			System.out.println("3. Revise");
			int input = sc.nextInt();
			switch(input) {
			case 1:
				break;
			case 2:
				select(7);
				break;
			case 3:
				next = mem.remove(mem.indexOf(next));
				next = today +" "+next.substring(10);
				mem.addLast(next);
				break;
			default:
			}
			break;
		case 8: //iterate
			mem.addLast(mem.removeFirst());
			break;
		case 10: //print deep storage
			printMem(deep);
			break;
		case 11: //add to deep storage
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			input--;
			deep.add(mem.remove(input));
			break;
		case 12: //move to mem
			System.out.println("Select:");
			printMem(deep);
			input = sc.nextInt();
			input--;
			mem.add(deep.remove(input));
			break;
		default:
			System.out.println("Not an option.");
		}
	}

	/**
	 * Prints out an ArrayDeque with formatting
	 * @param mem the ArrayDeque to be printed
	 */
	private static void printMem(LinkedList<String> mem) {
		int index = 1;
		for(String s : mem) {
			System.out.println(index + ". " + s);
			index++;
		}
	}
}
