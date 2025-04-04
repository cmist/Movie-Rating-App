package dbadapter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import datatypes.TimeDate;

/**
 * Class representing an offer
 * 
 * @author swe.uni-due.de
 *
 */
public class Movie {



	private int movieID;
	private String title;
	private String director;
	private String mainActors;
	private Date publishingDate;
	private int avgRating;
	
	private ArrayList<Rating> ratings;

	public Movie(int movieID, String title, String director, String mainActors, Date publishingDate) {
		this.movieID = movieID;
		this.title = title;
		this.director = director;
		this.mainActors = mainActors;
		this.publishingDate = publishingDate;
	}

	public int getMovieID() {
		return movieID;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public String getMainActors() {
		return mainActors;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public ArrayList<Rating> getRatings() {
		return ratings;
	}
	
	public int getAvgRating() {
		return avgRating;
	}

	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}
}
