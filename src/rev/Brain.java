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
	
	static LinkedList<Poem> mem = new LinkedList<>();
	static LinkedList<Poem> deep = new LinkedList<>();
	
	public static void main(String[] args) {
		
		String choice;
		
		for(String s:memory) {
			if(s.equals("deep"))
				enterDeep = true;
			else if(enterDeep)
				deep.add(new Poem(s));
			else
				mem.add(new Poem(s));
		}
		
		System.out.println("Today is "+today);
		
		printOptions();
		while(working) {
			choice = sc.next();
			System.out.println();
		
			select(choice);
			System.out.println("\n----------------\n");
		}
		
		//put back into storage
		LinkedList<String> out = new LinkedList<>();
		addAll(out,mem);
		out.add("deep");
		addAll(out,deep);
		ret.write(out);
	}
	
	public static void addAll(LinkedList<String> ll, LinkedList<Poem> lp) {
		for(int i=0; i<lp.size(); i++) {
			ll.add(lp.get(i).toString());
		}
	}

	private static void printOptions() {
		System.out.println("Choose:");
		System.out.println("0. Display Options");
		System.out.println("1. Display Memory");
		System.out.println("2. Quit");
		System.out.println("3. Submit");
		System.out.println("4. Remove");
		System.out.println("5. Edit");
		System.out.println("6. Revised");
		System.out.println("7. Choose at random");
		System.out.println("8. Iterate Loop");
		System.out.println("9. Display Oldest Memory");
		System.out.println("10. View Deep Storage");
		System.out.println("11. Move to Deep Storage");
		System.out.println("12. Move to Main Storage");
	}

	private static void select(String choice) {
		switch(choice) {
		case "0":
		case "options":
		case "o":
		case "op":
		case "help":
		case "h":
			printOptions();
			break;
		case "1": //print memory
		case "m":
		case "mem":
		case "print":
			printMem(mem);
			break;
		case "2": //quit
		case "q":
		case "quit":
			working = false;
			break;
		case "3": //submit
		case "s":
		case "sub":
		case "submit":
		case "add":
			System.out.print("Submit ");
			while(submitting) {
				System.out.println("Next: (type #done to quit)");
				next = sc.next();
				if(next.equals("#done")) submitting = false;
				else {
					String out = today + " " + next + sc.nextLine();
					System.out.println(out);
					mem.add(new Poem(out));
				}
			}
			submitting = true;
			break;
		case "4": //remove
		case "rem":
		case "remove":
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			mem.remove(input-1);
			break;
		case "5": //edit
		case "e":
		case "edit":
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			input--;
			System.out.println("New Input:");
			String edit = mem.remove(input).toString();
			edit = edit.substring(0,10);
			next = sc.next();
			mem.add(input,new Poem(edit +" "+ next + sc.nextLine()));
			break;
		case "6": //revised
		case "r":
		case "rev":
		case "revise":
		case "revised":
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			input--;
			next = mem.remove(input).toString();
			next = today +" "+next.substring(10);
			mem.addLast(new Poem(next));
			break;
		case "7": //random
		case "rand":
		case "random":
			int rand = (int) Math.ceil(Math.random()*mem.size());
			next = mem.get(rand).toString();
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
				select("7");
				break;
			case 3:
				next = mem.remove(mem.indexOf(next)).toString();
				next = today +" "+next.substring(10);
				mem.addLast(new Poem(next));
				break;
			default:
			}
			break;
		case "8": //iterate
		case "i":
		case "iter":
		case "iterate":
			mem.addLast(mem.removeFirst());
			break;
		case "9":
		case "old":
		case "oldest":
			LinkedList<Poem> old = new LinkedList<>();
			old.add(mem.getFirst());
			int compare;
			for(Poem s:mem) {
				if(!old.getFirst().equals(s)) {
					compare = old.getFirst().compareTo(s);
					if(compare > 0) {
						old.clear();
						old.add(s);
					}else if(compare == 0) {
						old.add(s);
					}
				}
			}
			printMem(old);
			break;
		case "10": //print deep storage
		case "d":
		case "ds":
		case "deep":
		case "deepStorage":
			printMem(deep);
			break;
		case "11": //add to deep storage
		case "ad":
		case "addDeep":
			System.out.println("Select:");
			printMem(mem);
			input = sc.nextInt();
			input--;
			deep.add(mem.remove(input));
			break;
		case "12": //move to mem
		case "rd":
		case "remDeep":
		case "removeDeep":
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
	private static void printMem(LinkedList<Poem> mem) {
		int index = 1;
		for(Poem s : mem) {
			System.out.println(index + ". " + s.toString());
			index++;
		}
	}
}
