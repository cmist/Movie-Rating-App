package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.VRApplication;
import datatypes.TimeDate;

/**
 * Contains GUI for Unregistered User
 * 
 * @author swe.uni-due.de
 *
 */
public class UnregisteredUserGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet contains the insertOffer form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// set pagetitle und navtype
		request.setAttribute("navtype", "unregisteredUser");
		request.setAttribute("pagetitle", "Register a new user");

		// Dispatch request to template engine
		try {
			request.getRequestDispatcher("/templates/defaultWebpageU.ftl").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Contains handling of insertOffer call
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("navtype", "staffmember");

		// Check wether the call is insertOffer or not
		if (request.getParameter("action").equals("register")) {

			// Append parameter of request
			String username = (String) request.getParameter("username");
			String email = (String) request.getParameter("email");
			int age = Integer.parseInt(request.getParameter("age"));

			// Call application to insert offer
			Boolean confirm = new VRApplication().forwardRegister(email, age, username);

			// Dispatch message to template engine
			try {
				request.setAttribute("pagetitle", "Insert Offer");
				
				if (confirm == true) {
					
					request.setAttribute("message", "New user has been registred successfully in the database.");
					request.getRequestDispatcher("/templates/showConfirm.ftl").forward(request, response);
					
				} else {
					
					request.setAttribute("message", "Error while registering user. Please check the input!");
					request.getRequestDispatcher("/templates/showDeny.ftl").forward(request, response);
					
				}
				
				

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			// Call doGet if request is not equal to insertOffer
		} else
			doGet(request, response);

	}
}
 