package com.ninza.hrm.api.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
Fileutility fLib= new Fileutility();
Connection conn;
ResultSet result;

public void getDbConnection()
{
	try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		conn=DriverManager.getConnection(fLib.getDataFromPropertiesFile("BDurl")
				,fLib.getDataFromPropertiesFile("DB_UserName")
				,fLib.getDataFromPropertiesFile("DB_Password"));
	}
	catch (Exception e) {
		
	}
}

public boolean executeQueryVerifyAndGetData(String query, int columnIndex, String expectedData) throws SQLException
{
	boolean flag=false;
	result = conn.createStatement().executeQuery(query);
	while(result.next()) {
		if(result.getString(columnIndex).equals(expectedData)){
			flag=true;
			break;
		}
	}
	if(flag==true) {
		System.out.println(expectedData +"is verified in database");
		return true;
	}
	else {
		System.out.println(expectedData +"is not verified in database");
		return false;
	}
}


	public void closeconnection() throws SQLException{
		try {
			conn.close();
		}
		catch (Exception e) {
			
		}	
	}
	
}

