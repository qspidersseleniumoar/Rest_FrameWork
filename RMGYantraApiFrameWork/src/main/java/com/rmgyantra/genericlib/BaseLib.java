package com.rmgyantra.genericlib;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static io.restassured.RestAssured.*;

import java.sql.SQLException;

/**
 * 
 * @author Anusha
 *
 */
public class BaseLib {

	/**
	 * to initialize the base URI, port and authentication
	 */
	@BeforeSuite
	public void config() {
		
		DataBaseUtilities.connectToDB();
		baseURI="http://studentapp";
		port=8084;
		basic("username", "password");


	}
	
	@AfterSuite
	public void afterSuite() throws SQLException {
		DataBaseUtilities.closeDb();
	}
}
