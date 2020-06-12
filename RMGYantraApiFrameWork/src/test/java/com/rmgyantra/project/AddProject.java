package com.rmgyantra.project;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rmgyantra.genericlib.BaseLib;
import com.rmgyantra.genericlib.DataBaseUtilities;
import com.rmgyantra.genericlib.IEndPoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class AddProject  extends BaseLib{
	
	
	
	@Test
	public void addSingleProjectTest() throws Throwable {
		String actDbProjectNAme = null;
		String actprojStatus = null;
		
		/* execute API */
		FileInputStream fis = new FileInputStream(new File("./resource/AddSingleProject.json"));
      Response resp =  given()
                         .contentType(ContentType.JSON)
                         .body(IOUtils.toByteArray(fis))
         
                          .when()
                          .post(IEndPoints.add_Single_Project);
                          
         
              resp.then().assertThat().statusCode(201);
              
              long actrespTime = resp.timeIn(TimeUnit.MILLISECONDS);
          
              /*capture data from response*/
              String   actprojectNAme = resp.jsonPath().get("projectName").toString();
             // String   actStatus = resp.jsonPath().get("status").toString();
		    
		
              /* Connect to Db & get the status of the Project & Project Name*/
              ResultSet resut = DataBaseUtilities.execyteQuery("select * from project where project_name = '"+actprojectNAme+"'");
              
              /*validation */
              while(resut.next()) {
            	  actDbProjectNAme = resut.getString(4);
            	  actprojStatus = resut.getString(5);
              }

		      
           Assert.assertEquals(actprojStatus, "Created");
           Assert.assertEquals(actprojectNAme, actDbProjectNAme);
	}

}
