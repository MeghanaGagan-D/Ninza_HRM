package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.endpoints.EndPoint;
import com.ninza.hrm.api.pojoUtility.NinjaProject;

import io.restassured.response.Response;

public class ProjectTest extends BaseAPIClass{
	NinjaProject pojoObj;


	@Test
	public void addProjectWithCompletedStats() throws Exception {
		
		String expSuccessMsg = "Successfully Added";
		String projectName = "scrim" + jLib.getRandomnumber();
		

		pojoObj = new NinjaProject("Meghana", projectName, "Created", 0);

		Response res = given()
								.spec(reqSpecObj)
								.body(pojoObj)
		.when().post(EndPoint.ADDProject);
		res.then().assertThat()
								.statusCode(201).assertThat().time(Matchers.lessThan(3000L)).assertThat()
								.spec(respSpecObj)
								.log().all();
		// verify the Project name in API layer
		String actSucessMsg = res.jsonPath().get("msg");
		Assert.assertEquals(expSuccessMsg, actSucessMsg);

		// verify the Project name in database layer
		boolean flag=dLib.executeQueryVerifyAndGetData("select * from project;", 4, projectName);
		Assert.assertTrue(flag, "Project name is not verified in database");
	}

	@Test(dependsOnMethods = "addProjectWithCompletedStats")
	public void addDuplicateProject() throws Exception {
		
		 given().spec(reqSpecObj)
		 		.body(pojoObj).when()
				.post(EndPoint.ADDProject)
		 .then().assertThat().statusCode(409)
		 		.spec(respSpecObj)
		 		.log().all();
	}

}
