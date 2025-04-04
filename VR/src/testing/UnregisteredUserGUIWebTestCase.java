package testing;


import net.sourceforge.jwebunit.junit.WebTester;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UnregisteredUserGUIWebTestCase {
    private WebTester;

    //Create a new WebTEster object that performs the test
    @Before
    public void prepare() {
        
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/VR/")
    }

    @Test
    public void testForwardRegister() {
        tester.beginAt("UnregisteredUser?page=register");

        tester.assertTitleEquals("MovieRatingApp - UnregisteredUser");
        tester.assertFormPresent();
        tester.assertTextPresent("Username");
        tester.assertFormElementPresent("Username");
        tester.assertTextPresent("Email");
        tester.assertFormElementPresent("Email");
        tester.assertTextPresent("Age");
        tester.assertFormElementPresent("Age");
        tester.SubmitButtonPresent("submit");

        //Submit the form with given parameters
        tester.setTextField("Username", "TestUserName");
        tester.setTextField("Email", "TestEmail");
        tester.setTextField("Age", "TestAge");
        
        //The application doesn't find the "submit" button, even though it's there
        tester.clickButton("submit");
        tester.assertTextPresent("New user has been registred successfully in the database.");

    }
}