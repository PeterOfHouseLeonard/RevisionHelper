package rev;

import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;

public class Brain {
	public static void main(String[] args) {
		Retrieve ret = new Retrieve();
		LinkedList<String> memory = ret.retrieve();
		Scanner sc = new Scanner(System.in);
		boolean working = true;
		boolean submitting = true;
		boolean enterDeep = false;
		int indexOfDeep = memory.indexOf("deep");
		int choice = 0;
		int input;
		LocalDate today = LocalDate.now();
		
		LinkedList<String> mem = new LinkedList<>();
		LinkedList<String> deep = new LinkedList<>();
		
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
			System.out.println("6. Revised"); //Add this
			System.out.println("7. View Deep Storage");
			System.out.println("8. Move to Deep Storage");
			System.out.println("9. Move to Main Storage");
			
			choice = sc.nextInt();
			System.out.println();
		
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
					String next = sc.next();
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
				String next = sc.next();
				mem.add(input,edit +" "+ next + sc.nextLine());
				break;
			case 7: //print deep storage
				printMem(deep);
				break;
			case 8: //add to deep storage
				System.out.println("Select:");
				printMem(mem);
				input = sc.nextInt();
				input--;
				deep.add(mem.remove(input));
				break;
			case 9: //move to mem
				System.out.println("Select:");
				printMem(deep);
				input = sc.nextInt();
				input--;
				mem.add(deep.remove(input));
				break;
			default:
				System.out.println("Not an option.");
			}
			System.out.println("\n----------------\n");
		}
		
		//put back into storage
		LinkedList<String> out = new LinkedList<>(mem);
		out.add("deep");
		out.addAll(deep);
		ret.write(out);
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
