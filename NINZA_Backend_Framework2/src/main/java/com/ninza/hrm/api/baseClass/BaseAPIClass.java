package com.ninza.hrm.api.baseClass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.Fileutility;
import com.ninza.hrm.api.genericUtility.JavaUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	
	public JavaUtility jLib= new JavaUtility();
	public Fileutility fLib= new Fileutility();
	public DataBaseUtility dLib= new DataBaseUtility();
	public static RequestSpecification reqSpecObj;
	public static ResponseSpecification respSpecObj;
	
@BeforeSuite
public void configBS() throws Exception
{
	dLib.getDbConnection();
	System.out.println("==========Connected to database========");
	
	RequestSpecBuilder builder= new RequestSpecBuilder();
	builder.setContentType(ContentType.JSON);
	//builder.setAuth(oauth2("token"));
	builder.setBaseUri(fLib.getDataFromPropertiesFile("BASEUri"));
	reqSpecObj = builder.build();
	
	ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
	resBuilder.expectContentType(ContentType.JSON);
	respSpecObj = resBuilder.build();
	
}

@AfterSuite
public void configAs() throws SQLException {
	dLib.closeconnection();
	System.out.println("==========Dis-connected from database========");
}
}
