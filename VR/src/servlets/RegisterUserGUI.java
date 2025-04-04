package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.VRApplication;
import datatypes.TimeDate;
import dbadapter.DBFacade;
import dbadapter.Movie;

/**
 * Contains GUI for staffmember
 * 
 * @author swe.uni-due.de
 *
 */
public class RegisterUserGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet contains the insertOffer form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
 
		try {
			
			// set pagetitle und navtype
			request.setAttribute("navtype", "registeredUser");
			
			
			if (request.getParameter("page").equals("MovieOverview")) {
				
				List<Movie> moviesList = new VRApplication().forwardMovieOverview();
				request.setAttribute("moviesList", moviesList);
				request.setAttribute("pagetitle", "MovieOverview");
				request.getRequestDispatcher("/templates/MovieOverview.ftl").forward(request, response);
				
			} else if (request.getParameter("page").equals("AddMovie")){
				request.setAttribute("pagetitle", "AddMovie");
				request.getRequestDispatcher("/templates/AddMovie.ftl").forward(request, response);
			} else if (request.getParameter("page").equals("RateMovie")){
				request.setAttribute("pagetitle", "RateMovie");
				request.setAttribute("movieID", request.getParameter("movieID"));
				request.setAttribute("users", DBFacade.getInstance().getUsers()); 
				request.getRequestDispatcher("/templates/RateMovie.ftl").forward(request, response);
			} else {
				
				request.setAttribute("pagetitle", "defaultWebpageR");
				request.getRequestDispatcher("/templates/defaultWebpageR.ftl").forward(request, response);
			}
		

			
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Contains handling of insertOffer call
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		Boolean confirm = false;
		
		request.setAttribute("navtype", "registeredUser");
		
 
		if (request.getParameter("action").equals("AddMovie")) {
			
			request.setAttribute("pagetitle", "Add Movie");
			
			try {
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
				// Append parameter of request   String title, String director, TimeDate actors, TimeDate publishingDate
				String title = (String) request.getParameter("title");
				String director = (String) request.getParameter("director");
				String actors = (String) request.getParameter("actors");
				Date publishingDate = dateFormat.parse((String)request.getParameter("publishingDate"));
	
				
				// Call application to insert offer
				confirm = new VRApplication().forwardAddMovie(title, director, actors, publishingDate);
	
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// Call doGet if request is not equal to insertOffer
		} else if  (request.getParameter("action").equals("RateMovie")) {
			
			request.setAttribute("pagetitle", "Rate Movie");
 
			int movieID = Integer.parseInt(request.getParameter("movieID"));
			String userName = (String) request.getParameter("userName");
			int rating = Integer.parseInt(request.getParameter("rating"));
			String comment = (String) request.getParameter("comment");
			
			// Call application to insert offer
			confirm = new VRApplication().forwardRateMovie(movieID, userName, rating, comment);

 
 
		} 

		try {
		
			if (confirm == true) {
				
				request.setAttribute("message", "the action '" + request.getParameter("action") + "' has been successfully");
				request.getRequestDispatcher("/templates/showConfirm.ftl").forward(request, response);
				
			} else {
				
				request.setAttribute("message", "Error while executing the action '" + request.getParameter("action") + "'. Please check the input!");
				request.getRequestDispatcher("/templates/showDeny.ftl").forward(request, response);	
			}
		
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}