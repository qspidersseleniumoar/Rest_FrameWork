package com.rmgyantra.project;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rmgyantra.genericlib.BaseLib;
import com.rmgyantra.genericlib.DataBaseUtilities;
import com.rmgyantra.genericlib.IEndPoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;



public class AddProjectWitONGoingStatus extends BaseLib{
	
	@Test
	public void addProjectONGoingStatusTest() throws Throwable {
		
		FileInputStream fis = new FileInputStream(new File("./resource/addProjectWitONGoingStatus.json"));
		
	       Response resp = given()
	          	            .contentType(ContentType.JSON)
	          	             .body(IOUtils.toByteArray(fis))
	                 	.when()
	          	             .post(IEndPoints.add_Single_Project);
	          	
	          resp.then().assertThat().statusCode(201);
	          
	          //capture the data from response
	          String apiProjectNAme = resp.jsonPath().get("projectName").toString();
	         // String apiProjectStatus = resp.jsonPath().get("status").toString();	
	          long respTime = resp.timeIn(TimeUnit.MILLISECONDS);
	          
	          //capture data from database
	          ResultSet result = DataBaseUtilities.execyteQuery("select * from project where project_name='"+apiProjectNAme+"'");
	          	
	          String dbProjectName = null;
	          String dbProjectStatus = null;
	          while(result.next()) {
	        	   dbProjectName = result.getString(4);
	        	   dbProjectStatus = result.getString(5);
	          }
	          
	          Assert.assertEquals(apiProjectNAme, dbProjectName);
	          Assert.assertEquals("On Going", dbProjectStatus); 	
	     
	}
	

}
