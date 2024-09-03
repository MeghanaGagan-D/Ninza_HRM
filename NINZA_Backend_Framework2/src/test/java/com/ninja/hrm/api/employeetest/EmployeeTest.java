package com.ninja.hrm.api.employeetest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.endpoints.EndPoint;
import com.ninza.hrm.api.pojoUtility.AddEmployeePojoClass;
import com.ninza.hrm.api.pojoUtility.NinjaProject;

public class EmployeeTest extends BaseAPIClass{
	
	@Test
	public void addEmployeTest() throws Exception {
	
		String projectName = "Spinz" + jLib.getRandomnumber();
		String userName = "masha" + jLib.getRandomnumber();
		
		// API-1 creating project
		NinjaProject pojoObj = new NinjaProject("Meghana", projectName, "Created", 0);

		given()
				.spec(reqSpecObj)
				.body(pojoObj)

				.when().post(EndPoint.ADDProject)
				.then().log().all();

		// API-2 creating employee and adding to same project
		AddEmployeePojoClass pobj = new AddEmployeePojoClass("QA", "05-05-1998", "masha@gmail.com", userName, 3.5,
				"9790654567", projectName, "Test Engineers", userName);

		given()
				.spec(reqSpecObj)
				.body(pobj)
				.when().post(EndPoint.ADDEmp)
				.then().assertThat()
									.time(Matchers.lessThan(3000L))
									.statusCode(201)
									.spec(respSpecObj)
									.log().all();
		
		//verify employee name database
		boolean flag =dLib.executeQueryVerifyAndGetData("select * from employee;", 5, userName);
		Assert.assertTrue(flag, "Employee name is not verified in database");

	}
	
	@Test
	public void addEmployeWithoutEmailTest() throws Exception {
		
		String projectName = "Spinz" + jLib.getRandomnumber();
		String userName = "masha" + jLib.getRandomnumber();

		// API-1 creating project
		NinjaProject pojoObj = new NinjaProject("Meghana", projectName, "Created", 0);

		given()
				.spec(reqSpecObj)
				.body(pojoObj)

		.when().post(EndPoint.ADDProject)
		.then().log().all();

		// API-2 creating employee and adding to same project
		AddEmployeePojoClass pobj = new AddEmployeePojoClass("QA", "05-05-1998", "", userName, 3.5,
				"9790654567", projectName, "Test Engineers", userName);

		given()
				.spec(reqSpecObj)
				.body(pobj)
		.when().post(EndPoint.ADDEmp)
		.then().assertThat()
							.statusCode(500)
							.spec(respSpecObj)
							.log().all();
	}
		
}
