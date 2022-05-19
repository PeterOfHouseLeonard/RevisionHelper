package rev;

public class Tester {

	public static void main(String[] args) {
		Poem p1 = new Poem("2022-05-10 title1");
		Poem p2 = new Poem("2023-07-03 title2");
		
		System.out.println(p2.compareTo(p1));
	}

}
