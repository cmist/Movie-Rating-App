package interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import datatypes.GuestData;
import datatypes.TimeDate;
import dbadapter.Rating;
import dbadapter.Movie;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author swe.uni-due.de
 *
 */
public interface IMovie {

	public ArrayList<Movie> getMovieOverview();

	public Boolean addingMovie(
			String title, String director, String actors, Date publishingDate);

	public Boolean ratingMovie(
			int movieID, String userName, int rating, String comment);
}
