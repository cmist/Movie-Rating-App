package datatypes;

/**
 * Contains Date informations about a Movie.
 * 
 * @author swe.uni-due.de
 *
 */
public class TimeDate {

	private int year;
	private int month;
	private int day;

	public TimeDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}

}
