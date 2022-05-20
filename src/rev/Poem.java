package rev;

/**
 * Contains the data for a poem entry
 * @author Peter Leonard
 * Does not account for leap years
 */
public class Poem{
	/**
	 * the last date this poem was changed
	 */
	private int year, month, day;
	/**
	 * this poem's title
	 */
	private String title;
	
	
	public Poem(String input) {
		year = Integer.valueOf(input.substring(0,4));
		month = Integer.valueOf(input.substring(5,7));
		day = Integer.valueOf(input.substring(8,10));
		title = input.substring(11);	
	}

	/**
	 * compares the date values of two poems
	 * @param p the poem this one is being compared to
	 * @return int difference
	 */
	public int compareTo(Poem p) {
		int difference = 0;
		difference += ((this.year - p.year)*365);
		difference += monthToDays(this.month)-monthToDays(p.month);
		difference += this.day - p.day;
		return difference;
	}
	
	/**
	 * changes the month to its equivalent value in days
	 * @param month the month to be changed
	 * @return number of days since beginning of year
	 */
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
	
	public String toString() {
		return year +"-"+ month +"-"+ day +" "+ title;
	}
}
