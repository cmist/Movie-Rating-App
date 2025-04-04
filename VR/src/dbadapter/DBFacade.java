package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import datatypes.TimeDate;
import datatypes.GuestData;
import interfaces.IDataBase;
import interfaces.IMovie;

/**
 * Class which acts as the connector between application and database. Creates
 * Java objects from SQL returns. Exceptions thrown in this class will be
 * catched with a 500 error page.
 * 
 * @author swe.uni-due.de
 *
 */
public class DBFacade implements IMovie, IDataBase {
	private static DBFacade instance;

	/**
	 * Constructor which loads the corresponding driver for the chosen database type
	 */
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	public static void setInstance(DBFacade dbfacade) {
		instance = dbfacade;
	}

	/**
	 * Function that returns all movies from the database.
	 * 
	 * @return Arraylist of all movies objects.
	 */
	public ArrayList<Movie> getMovieOverview() {
		ArrayList<Movie> result = new ArrayList<Movie>();

		// Declare the necessary SQL queries.
		String queryMovieOverview = "SELECT * from Movies";
		String queryMovieRatings = "SELECT avg(rating) from Rates WHERE movieID= ?";
		
	 
		// Query all offers that fits to the given criteria.
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(queryMovieOverview);
					PreparedStatement psMovieRatings = connection.prepareStatement(queryMovieRatings)) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {

						Movie temp = new Movie(rs.getInt("movieID"), rs.getString("title"), rs.getString("director"),rs.getString("actors"), rs.getDate("publishingDate"));
						
						psMovieRatings.setInt(1, temp.getMovieID());

						try (ResultSet rsMovieRatings = psMovieRatings.executeQuery()) {
							if (rsMovieRatings.next()) {
								temp.setAvgRating(rsMovieRatings.getInt(1));
							}						
						}
						result.add(temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result.sort(Comparator.comparing(Movie::getAvgRating).reversed());

		return result;
	}

	/**
	 * Register a user in the database.
	 * 
	 * @param emailAddress
	 * @param age
	 * @param userName
	 * @return confirm(Boolean)
	 */
	public Boolean registering(String emailAddress, int age, String userName) {

		// Declare SQL queries to check if the user is unique and insert the user data.
		String queryUsername = "SELECT count(*) from Users WHERE username = ?";
		String insertRegistering = "INSERT INTO Users (username, emailAddress, age) VALUES (?, ?, ?)"; 
 
		
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(insertRegistering);
					PreparedStatement psUsername = connection.prepareStatement(queryUsername)) {
				
				psUsername.setString(1, userName);

				try (ResultSet resultSet = psUsername.executeQuery()) {
					if (resultSet.next()) {
						int existing = resultSet.getInt(1);
						
						if (existing == 0 && age >= 18) {
							
							ps.setString(1, userName);
							ps.setString(2, emailAddress);
							ps.setInt(3, age);
							
							ps.executeUpdate();
							
							return true;
					 
						}
					}
				}		
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;

	}

	@Override
	public Boolean addingMovie(String title, String director, String actors, Date publishingDate) {
		
		// Declare SQL queries to check if the user is unique and insert the user data.
		String queryMovie = "SELECT count(*)  from Movies WHERE title= ?";
		String insertAddingMovie = "INSERT INTO Movies (title, director, actors, publishingDate) VALUES (?, ?, ?, ?)"; 
 
		
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(insertAddingMovie);
					PreparedStatement psUsername = connection.prepareStatement(queryMovie)) {
				
				psUsername.setString(1, title);

				try (ResultSet resultSet = psUsername.executeQuery()) {
					if (resultSet.next()) {
						int existing = resultSet.getInt(1);
						
						if (existing == 0) {
							
							ps.setString(1, title);
							ps.setString(2, director);
							ps.setString(3, actors);
							ps.setTimestamp(4, new Timestamp(publishingDate.getTime()));
							
							ps.executeUpdate();
							
							return true;
					 
						}
					}
				}		
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean ratingMovie(int movieID, String userName, int rating, String comment) {
		
		// Declare SQL queries to check if the user is unique and insert the user data.
		
		String queryRate = "SELECT count(*) from Rates WHERE movieID=? AND username= ?";
		String insertRatingMovie = "INSERT INTO Rates (movieID, username, rating, comment) VALUES (?, ?, ?, ?)"; 
 
		
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(insertRatingMovie);
					PreparedStatement psUsername = connection.prepareStatement(queryRate)) {
				psUsername.setInt(1, movieID);
				psUsername.setString(2, userName);

				try (ResultSet resultSet = psUsername.executeQuery()) {
					if (resultSet.next()) {
						int existing = resultSet.getInt(1);
						
						if (existing == 0 && rating > 0 && rating <= 10) {
							ps.setInt(1, movieID);
							ps.setString(2, userName);
							ps.setInt(3, rating);
							ps.setString(4, comment);
 
							ps.executeUpdate();
							
							return true;
					 
						}
					}
				}		
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public ArrayList<String> getUsers() {
		ArrayList<String> result = new ArrayList<String>();

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement("SELECT username from users")) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						result.add(rs.getString("username"));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return result;
	}
	
 
}
