package dbadapter;

import java.sql.Timestamp;

import datatypes.GuestData;
import datatypes.TimeDate;

/**
 * Class representing a booking
 * 
 * @author swe.uni-due.de
 *
 */
public class Rating {
	String username;
	String comment;
	int rating;
	
	public Rating(int rating, String username, String comment) {
		this.rating = rating;
		this.username = username;
		this.comment = comment;
	}
	
	public String getUsername() {
		return username;
	}
	public String getComment() {
		return comment;
	}
	public int getRating() {
		return rating;
	}
 
}
