package rev;

public class Poem{
	private int year, month, day;
	private String title;
	private int LeapYears = 0;
	
	public Poem(String input) {
		year = Integer.valueOf(input.substring(0,4));
		month = Integer.valueOf(input.substring(5,7));
		day = Integer.valueOf(input.substring(8,10));
		title = input.substring(11);	
	}

	public int compareTo(Poem p) {
		int difference = 0;
		difference += ((this.year - p.year)*365)+LeapYears;
		difference += monthToDays(this.month)-monthToDays(p.month);
		difference += this.day - p.day;
		return difference;
	}
	
	public int monthToDays(int month) {
		int days = 0;
		for(int i=0; i<month; i++) {
			if(i==1||i==3||i==5||i==7||i==8||i==10||i==12) {
				days += 31;
			}else if(i==2) {
				days += 28;
			}else {
				days += 30;
			}
		}
		return days;
	}
}
