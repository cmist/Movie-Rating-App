package interfaces;

import java.util.ArrayList;
import java.util.Date;

import datatypes.TimeDate;
import dbadapter.Movie;

/**
 * Interface that provides all methods for the interaction with the Registered User.
 * 
 * @author swe.uni-due.de
 *
 */
public interface RUCmds {
	
	public ArrayList<Movie> forwardMovieOverview();

	public Boolean forwardAddMovie(
			String title, String director, String actors, Date publishingDate);
	
	public Boolean forwardRateMovie(
			int movieID, String userName, int rating, String comment);
	
}
