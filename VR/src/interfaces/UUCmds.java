package interfaces;

import java.util.ArrayList;

import datatypes.GuestData;
import dbadapter.Rating;
import dbadapter.Movie;

/**
 * Interface that provides all method to interact with a guest.
 * 
 * @author swe.uni-due.de
 *
 */
public interface UUCmds {

	public Boolean forwardRegister(String emailAddress, int age, String userName);

}
