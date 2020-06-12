package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JDBCTEst {

	public static void main(String[] args) throws Throwable {
		
/*		
				//step 1 : REgister/load the JDBC
		Driver driverRef = new Driver();
	     DriverManager.registerDriver(driverRef);
	     
	
		//step 2: connect to database
	    Connection con  =  DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "admin");
		
		//step 3 : issue/ create sql Query
	    Statement stat = con.createStatement();
	    String query = "select * from project";
		
		//step 4 : execute Query  / validate
	    ResultSet result = stat.executeQuery(query);
	    
	         while(result.next()) {
	        	 System.out.println(result.getString(1) + "\t" + result.getString(2)+ "\t" + result.getString(3)+ "\t" + result.getString(4)+ "\t" + result.getString(5)+ "\t" + result.getInt(6));
	         }
		
		//step 5 : close the connection
		con.close();*/

		
		
		//step 1 : REgister/load the JDBC
		Driver driverRef = new Driver();
	     DriverManager.registerDriver(driverRef);
	     
	
		//step 2: connect to database
	    Connection con  =  DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "admin");
		
		//step 3 : issue/ create sql Query
	    Statement stat = con.createStatement();
	    String query = "select * from project where project_name = 'deb-1';";
		
		//step 4 : execute Query  / validate
	       ResultSet result = stat.executeQuery(query);
	       
	       while(result.next()) {
         	  System.out.println(result.getString(4));
         	  System.out.println(result.getString(5));
           }
		      
      
		
		//step 5 : close the connection
		con.close();
		
		
	}

}
