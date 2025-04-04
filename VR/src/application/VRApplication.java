package application;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import datatypes.TimeDate;
import datatypes.GuestData;
import dbadapter.Rating;
import dbadapter.DBFacade;
import dbadapter.Movie;
import interfaces.UUCmds;
import interfaces.RUCmds;

/**
 * This class contains the VRApplication which acts as the interface between all
 * components.
 * 
 * @author swe.uni-due.de
 *
 */
public class VRApplication implements UUCmds, RUCmds {

	private static VRApplication instance;

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static VRApplication getInstance() {
		if (instance == null) {
			instance = new VRApplication();
		}

		return instance;
	}

	
	@Override
	public Boolean forwardAddMovie(String title, String director, String actors, Date publishingDate) {
		Boolean confirmDeny = false;

		try {

			confirmDeny = DBFacade.getInstance().addingMovie(title, director, actors, publishingDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return confirmDeny;
	}

	@Override
	public Boolean forwardRegister(String emailAddress, int age, String userName) {
		// Create result object
		Boolean confirmDeny = false;

		try {

			confirmDeny = DBFacade.getInstance().registering(emailAddress, age, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return confirmDeny;
		
	}

	@Override
	public ArrayList<Movie> forwardMovieOverview() {
		return DBFacade.getInstance().getMovieOverview();
	}

	@Override
	public Boolean forwardRateMovie(int movieID, String userName, int rating, String comment) {
		Boolean confirmDeny = false;

		try {
			
			confirmDeny = DBFacade.getInstance().ratingMovie(movieID, userName, rating, comment);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return confirmDeny;
	}
	
}
